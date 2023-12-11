package com.green.greengram3.dm;


import com.green.greengram3.common.ResVo;
import com.green.greengram3.dm.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DmService {
    private final DmMapper mapper;

    public ResVo postDmUser() {
        return null;
    };

    public ResVo postDmMsg(DmMsgInsDto dto) {
        int affectedRows = mapper.insDmMsg(dto);
        return new ResVo(dto.getSeq());
    }

    public List<DmMsgSelVo> getMsgAll(DmMsgSelDto dto) {
        return mapper.selDmMsgAll(dto);
    }

    public List<DmSelVo> getDmAll(DmSelDto dto) {
        return mapper.selDmAll(dto);
    }

    public ResVo delDmMsg(DmMsgDelDto dto) {
        int result = mapper.delDmMsg(dto);
        return new ResVo(result);
    }
}
