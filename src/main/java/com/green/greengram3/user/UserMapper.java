package com.green.greengram3.user;


import com.green.greengram3.user.model.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    int userSignup(UserSignupProcDto dto); // select 제외한 crud는 전부 int 아님 void
    UserSigninProcVo selUser(UserSigninDto dto);

    int delFollow(UserFollowDto dto);
    int insFollow(UserFollowDto dto);

    UserInfoVo selUserInfo(UserInfoSelDto dto);
    //쿼리문 결과값이 컬럼 1개만 나오기때문에 List안써도됨
}
