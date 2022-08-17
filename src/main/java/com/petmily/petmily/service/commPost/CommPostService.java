package com.petmily.petmily.service.commPost;

import com.petmily.petmily.dto.commPost.CommPostDto;
import com.petmily.petmily.dto.commPost.CommPostReq;
import com.petmily.petmily.model.ServiceCategory;
import com.petmily.petmily.model.User;
import com.petmily.petmily.model.commPost.CommPost;
import com.petmily.petmily.model.commPost.CommPostImg;
import com.petmily.petmily.model.commPost.CommPostLike;
import com.petmily.petmily.repository.commPost.CommPostCommentRepository;
import com.petmily.petmily.repository.commPost.CommPostImgRepository;
import com.petmily.petmily.repository.commPost.CommPostLikeRepository;
import com.petmily.petmily.repository.commPost.CommPostRepository;
import com.petmily.petmily.util.FileProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommPostService {

    private final CommPostRepository commPostRepository;
    private final CommPostImgRepository commPostImgRepository;
    private final CommPostCommentRepository commentRepository;
    private final CommPostLikeRepository likeRepository;
    private final FileProcessService processService;

    @Autowired
    public CommPostService(CommPostRepository commPostRepository, CommPostImgRepository commPostImgRepository, CommPostCommentRepository commentRepository, CommPostLikeRepository likeRepository, FileProcessService processService) {
        this.commPostRepository = commPostRepository;
        this.commPostImgRepository = commPostImgRepository;
        this.commentRepository = commentRepository;
        this.likeRepository = likeRepository;
        this.processService = processService;
    }

    public CommPost createPost(User user, CommPostReq req){

        String title = req.getTitle();
        String contents = req.getContent();
        List<MultipartFile> imgs = req.getImgs();
        List<CommPostImg> uploadImgs = new ArrayList<>();

        CommPost commPost = new CommPost(title, contents, user);
        if (!imgs.isEmpty()) {
            imgs.stream().forEach(multipartFile -> {
                String url = processService.uploadFile(multipartFile, ServiceCategory.COMMPOST);
                CommPostImg img = new CommPostImg(url, user);
                uploadImgs.add(img);
            });
        }
        commPost.addImages(uploadImgs);
        commPostImgRepository.saveAll(uploadImgs);
        return commPostRepository.save(commPost);
    }

    public List<CommPost> retrieveAllPosts() {
        return commPostRepository.findAll();
    }

    public CommPost updatePost(Long postId, CommPostReq requestDto, User user) {
        CommPost commPost = commPostRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시물이 없습니다"));

//        commPost.setTitle(requestDto.getTitle());
//        commPost.setContent(requestDto.getContent());
//        commPost.setImgs(requestDto.getImgs());
        String title = requestDto.getTitle();
        String content = requestDto.getContent();
        List<MultipartFile> imgs = requestDto.getImgs();
        List<CommPostImg> uploadImgs = new ArrayList<>();

        if (commPost.getUser().getId().equals(user.getId())) {
            commPost.updatePost(title, content);
            if (!imgs.isEmpty()) {
                imgs.stream().forEach(multipartFile -> {
                    String url = processService.uploadFile(multipartFile, ServiceCategory.COMMPOST);
                    CommPostImg tmp = new CommPostImg(url, commPost);
                    uploadImgs.add(tmp);
                });
            }
        }
        commPost.addImages(uploadImgs);
        commPostImgRepository.saveAll(uploadImgs);

        return commPostRepository.save(commPost);
    }

    public CommPost deletePost(Long postId) {
        System.out.println("postId = " + postId);
        CommPost commPost = commPostRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시물이 없습니다"));

        commPostRepository.deleteById(postId);
        return commPost;
    }

    public CommPost addLike(Long postId, User user) {
        CommPost commPost = commPostRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시물이 없습니다."));
        CommPostLike like = new CommPostLike();

        if (!commPost.getUser().getId().equals(user.getId())) {
            Optional<CommPostLike> tmp = likeRepository.findByPostIdAndUserId(postId, user.getId());

            if (tmp.isEmpty()) {
                like = new CommPostLike(commPost, user);
            }
        }
        return likeRepository.save(like).getCommPost();
    }

}
