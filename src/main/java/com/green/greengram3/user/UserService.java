package com.green.greengram3.user;

import com.green.greengram3.common.Const;
import com.green.greengram3.common.ResVo;
import com.green.greengram3.user.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper mapper;

    public ResVo signup(UserSignupDto dto) {
        String salt = BCrypt.gensalt();
        String hashedPw = BCrypt.hashpw(dto.getUpw(), salt);
        //비밀번호 암호화

        UserSignupProcDto pDto = new UserSignupProcDto();
        pDto.setUid(dto.getUid());
        pDto.setUpw(hashedPw);
        pDto.setNm(dto.getNm());
        pDto.setPic(dto.getPic());

        log.info("before - pDto.iuser : {}", pDto.getIuser());
        int affectedRows = mapper.insUser(pDto);
        log.info("after - pDto.iuser : {}", pDto.getIuser());

        return new ResVo(pDto.getIuser()); //회원가입한 iuser pk값이 리턴
    }

    public UserSigninVo signin(UserSigninDto dto) {
        UserSelDto sDto = new UserSelDto();
        sDto.setUid(dto.getUid());

        UserEntity entity = mapper.selUser(sDto);
        if(entity == null) {
            return UserSigninVo.builder().result(Const.LOGIN_NO_UID).build();
        } else if(!BCrypt.checkpw(dto.getUpw(), entity.getUpw())) {
            return UserSigninVo.builder().result(Const.LOGIN_DIFF_UPW).build();
        }

        return UserSigninVo.builder()
                .result(Const.SUCCESS)
                .iuser(entity.getIuser())
                .nm(entity.getNm())
                .pic(entity.getPic())
                .build();
    }

    public UserInfoVo getUserInfo(UserInfoSelDto dto) {
        return mapper.selUserInfo(dto);
    }

    public ResVo patchUserFirebaseToken(UserFirebaseTokenPatchDto dto) {
        int affectedRows = mapper.updUserFirebaseToken(dto);
        return new ResVo(affectedRows);
    }

    public ResVo patchUserPic(UserPicPatchDto dto) {
        int affectedRows = mapper.updUserPic(dto);
        return new ResVo(affectedRows);
    }
    public ResVo toggleFollow(UserFollowDto dto) {
        int delAffectedRows = mapper.delUserFollow(dto);
        if(delAffectedRows == 1) {
            return new ResVo(Const.FAIL);
        }
        int insAffectedRows = mapper.insUserFollow(dto);
        return new ResVo(Const.SUCCESS);
    }
}
