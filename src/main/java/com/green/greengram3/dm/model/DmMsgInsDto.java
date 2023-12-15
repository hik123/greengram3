package com.green.greengram3.dm.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class DmMsgInsDto {


    private int idm;
    @JsonIgnore
    private int seq;
    private int loginedIuser;
    private String loginedPic; //상대방에 보여줄 나의 프로필 사진
    private String msg;
}
