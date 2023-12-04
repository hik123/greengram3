package com.green.greengram3.user;


import com.green.greengram3.common.ResVo;
import com.green.greengram3.user.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper mapper;

    public ResVo signup(UserSignupDto dto) {
        //비밀번호 암호화
        String hashedpw = BCrypt.hashpw(dto.getUpw(), BCrypt.gensalt());

        UserSignupProcDto pDto = UserSignupProcDto.builder()
                .uid(dto.getUid())
                .upw(hashedpw)
                .nm(dto.getNm())
                .pic(dto.getPic())
                .build();
        log.info(" before - pDto.getIuser() : {}", pDto.getIuser());
        int affetedRow = mapper.userSignup(pDto);
        log.info(" after - pDto.getIuser() : {}", pDto.getIuser());
        return new ResVo(pDto.getIuser()); //회원가입한 iuser pk값이 리턴
    }

    public UserSigninVo signin(UserSigninDto dto) {
        //result - 1:성공, 2:아이디 없음, 3:비번틀림
        UserSigninVo vo = new UserSigninVo();
        UserSigninProcVo pVo = mapper.selUser(dto);
        if(pVo == null) {
            vo.setResult(2);
            return vo;
        }
        if(BCrypt.checkpw(dto.getUpw(), pVo.getUpw())) { // BCrypt.checkpw(비밀번호, 암호화된 비밀번호)) <<결과값은 같을때true, 다를때false / 비번비교 순서중요
            vo.setResult(1);
            vo.setIuser(pVo.getIuser());
            vo.setNm(pVo.getNm());
            vo.setPic(pVo.getPic());
            return vo;
        }
        vo.setResult(3);
        return vo;
    }

    public ResVo toggleFollow(UserFollowDto dto) {
        int delAffectedRow = mapper.delFollow(dto);
        if(delAffectedRow == 1) {
            return new ResVo(0);
        }
        int insAffectedRow = mapper.insFollow(dto);
        return new ResVo(1);
    }
}
