package com.project.poinhanshin.service.mbti;

import com.project.poinhanshin.domain.mbti.MBTINameKind;
import com.project.poinhanshin.mapper.mbti.MBTIMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MBTIServiceImpl implements MBTIService{

    // DB 메서드 불러오기 위함
    MBTIMapper mbtiMapper;

    // 의존성 추가
    @Autowired
    public MBTIServiceImpl(MBTIMapper mbtiMapper) {
        this.mbtiMapper = mbtiMapper;
    }

    // 테스트 결과값에 대응되는 MBTI 데이터를 불러온다.
    @Override
    public List<MBTINameKind> serviceSearchAband(Integer MBTI) {
        List<MBTINameKind> mbtInameKindList =  mbtiMapper.searchAband(MBTI);
        return mbtInameKindList;
    }
}
