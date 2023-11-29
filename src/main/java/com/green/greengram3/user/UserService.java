package com.green.greengram3.user;


import com.green.greengram3.common.ResVo;
import com.green.greengram3.user.model.UserSignupDto;
import com.green.greengram3.user.model.UserSignupProcDto;
import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper mapper;

    public ResVo signup(UserSignupDto dto) {
        //비밀번호 암호화
        String hashpw = BCrypt.hashpw(dto.getUpw(), BCrypt.gensalt());

        UserSignupProcDto pDto = UserSignupProcDto.builder()
                .uid(dto.getUid())
                .upw(hashpw)
                .nm(dto.getNm())
                .pic(dto.getPic())
                .build();
        mapper.userSignup(pDto);
        return new ResVo(pDto.getIuser()); //회원가입한 iuser pk값이 리턴
    }
}
