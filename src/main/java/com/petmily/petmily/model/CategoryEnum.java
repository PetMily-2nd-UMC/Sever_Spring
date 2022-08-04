package com.petmily.petmily.model;

public enum CategoryEnum {
    DOG,
    CAT,
    REPTILE,
    FISH,
    PARROT,
    MOUSE
    ;

    private static CategoryEnum[] list = CategoryEnum.values();

    public static CategoryEnum getCategory(int i){
        return list[i];
    }

}
