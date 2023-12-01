package com.green.greengram3.user.model;

import lombok.Data;

@Data
public class UserEntity {   // entity >> 테이블과 구조가 똑같을때 붙임
    private int iuser;
    private String uid;
    private String upw;
    private String nm;
    private String pic;
    private String createdAt;
    private String updatedAt;
}
