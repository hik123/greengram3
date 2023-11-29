package com.green.greengram3.user;


import com.green.greengram3.user.model.UserSignupProcDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    int userSignup(UserSignupProcDto dto);
}
