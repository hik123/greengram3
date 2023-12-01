package com.green.greengram3.user.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Schema(title = "회원가입 데이터")
public class UserSignupDto {
    @Schema(title = "유저 id", example = "mic") //example 안써도됨
    private String uid;         //멤버필드 속성명은 프론트랑 맞춰야됨,

    @Schema(title = "유저 비밀번호")
    private String upw;

    private String nm;
    private String pic;
}
