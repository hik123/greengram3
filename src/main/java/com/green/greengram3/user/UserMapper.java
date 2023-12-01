package com.green.greengram3.user;


import com.green.greengram3.user.model.UserSigninDto;
import com.green.greengram3.user.model.UserSigninProcVo;
import com.green.greengram3.user.model.UserSignupProcDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    int userSignup(UserSignupProcDto dto); // select 제외한 crud는 전부 int 아님 void

    UserSigninProcVo selUser(UserSigninDto dto);
}
