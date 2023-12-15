package com.green.greengram3.user.model;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
public class UserSigninVo {
    private final int result;
    private int iuser;
    private String nm;
    private String pic;
    private String firebaseToken;
}