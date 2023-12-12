package com.green.greengram3.dm;

import com.green.greengram3.common.ResVo;
import com.green.greengram3.dm.model.*;
import com.green.greengram3.user.UserMapper;
import com.green.greengram3.user.model.UserEntity;
import com.green.greengram3.user.model.UserInfoSelDto;
import com.green.greengram3.user.model.UserSelDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class DmService {
    private final DmMapper mapper;
    private final UserMapper userMapper;

    public List<DmSelVo> getDmAll(DmSelDto dto) {
        return mapper.selDmAll(dto);
    }

    public DmSelVo postDm(DmInsDto dto) {
        Integer isExistDm = mapper.selDmUserCheck(dto);
        log.info("isExistDm: {}", isExistDm);
        if(isExistDm != null) { //이미 방이 있는 경우
            return null;
        }

        int dmAffectedRows = mapper.insDm(dto);
        int dmUserAffectedRows = mapper.insDmUser(dto);

        UserSelDto usDto = new UserSelDto();
        usDto.setIuser(dto.getOtherPersonIuser());

        UserEntity userEntity = userMapper.selUser(usDto);
        return DmSelVo.builder()
                .idm(dto.getIdm())
                .otherPersonIuser(userEntity.getIuser())
                .otherPersonNm(userEntity.getNm())
                .otherPersonPic(userEntity.getPic())
                .build();
    }


    public ResVo postDmMsg(DmMsgInsDto dto) {
        int insAffectedRows = mapper.insDmMsg(dto);
        //last msg update
        int updAffectedRows = mapper.updDmLastMsg(dto);
        return new ResVo(dto.getSeq());
    }
    public List<DmMsgSelVo> getMsgAll(DmMsgSelDto dto) {
        List<DmMsgSelVo> list = mapper.selDmMsgAll(dto);
        return list;
    }

    public ResVo delDmMsg(DmMsgDelDto dto) {
        int delAffectedRows = mapper.delDmMsg(dto);
        if(delAffectedRows == 1) {
            int updAffectedRows = mapper.updDmLastMsgAfterDelByLastMsg(dto);
        }
        return new ResVo(delAffectedRows);
    }
}
