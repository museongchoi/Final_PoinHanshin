package com.project.poinhanshin.service.api;

import com.project.poinhanshin.domain.api.KindDto;
import com.project.poinhanshin.mapper.api.ApiMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ApiServiceImpl implements ApiService{

    ApiMapper apiMapper;

    @Autowired
    public ApiServiceImpl(ApiMapper apiMapper) {
        this.apiMapper = apiMapper;
    }

    // 품종 리스트 갱신 메서드
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int renewal(KindDto[] kindDtoList) {
        // 갱신 전에 모두 삭제
        apiMapper.deleteAll();
        // 서버에서 가져온 품종 리스트를 하나하나 insert 수행
        for(int i = 0 ; i < kindDtoList.length; i++){
            apiMapper.insertData(kindDtoList[i]);
        }
        return 1;
    }

    // 품종 번호 검색 메서드
    @Override
    public String searchKind(String KNm) {
        return apiMapper.selectKindCd(KNm);
    }
}
