package com.green.greengram3.user;

import com.green.greengram3.user.model.*;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    int insUser(UserSignupProcDto dto);
    UserEntity selUser(UserSelDto dto);
    UserInfoVo selUserInfo(UserInfoSelDto dto);
    
    int insUserFollow(UserFollowDto dto);
    int delUserFollow(UserFollowDto dto);
}
