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
    private String msg;
}
