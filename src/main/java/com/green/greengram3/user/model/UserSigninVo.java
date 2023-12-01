package com.green.greengram3.user.model;


import lombok.Data;

@Data
public class UserSigninVo {         //로그인 했을때 받고싶은 데이터 (프론트와 상의)
                                //e.g. pk값, 이름, 프로필사진
    private int result;  // 코드값을 담기위한 값     e.g.비번틀림 - result:2
    private int iuser;
    private String nm;
    private String pic;
}
