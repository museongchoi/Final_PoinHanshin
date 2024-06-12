package com.project.poinhanshin.mapper.mbti;




import com.project.poinhanshin.domain.mbti.MBTINameKind;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MBTIMapper {

    // 품종 번호, 품종 이름, mbti 유형
    List<MBTINameKind> searchAband(Integer MBTI);

}
