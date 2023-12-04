package com.green.greengram3.user;

import com.green.greengram3.common.ResVo;
import com.green.greengram3.feed.FeedService;
import com.green.greengram3.user.model.UserFollowDto;
import com.green.greengram3.user.model.UserSigninDto;
import com.green.greengram3.user.model.UserSigninVo;
import com.green.greengram3.user.model.UserSignupDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    private final UserService service;

    @PostMapping("/signup")
    @Operation(summary = "회원가입", description = "회원가입 처리")
    /*@Parameters(value = {  //get방식일때만 쓰는게
            @Parameter(name = "uid", description = "아이디")
            , @Parameter(name = "upw", description = "비밀번호")
            , @Parameter(name = "nm", description = "이름")
            , @Parameter(name = "pic", description = "프로필 사진")
    })*/
    public ResVo postSignup(@RequestBody UserSignupDto dto) {
        log.info("dto: {}", dto); //데이터 잘 넘어오는지 항상 체크하기
        return service.signup(dto);
    }

    @PostMapping("/signin")
    public UserSigninVo postsignin(@RequestBody UserSigninDto dto) {
        log.info("dto : {}", dto);
        return service.signin(dto); //result - 1:성공, 2:아이디 없음, 3:비번틀림
    }

    // -- follow --
    // ResVo -result: 1 - following, 취소는 0    //없으면 인서트 있으면 삭제
    @PostMapping("/follow")
    public ResVo toggleFollow (@RequestBody UserFollowDto dto) {
        return service.toggleFollow(dto);
    }
}

