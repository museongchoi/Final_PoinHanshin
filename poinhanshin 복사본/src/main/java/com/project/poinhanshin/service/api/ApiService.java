package com.project.poinhanshin.service.api;

import com.project.poinhanshin.domain.api.KindDto;
import com.project.poinhanshin.mapper.api.ApiMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ApiService {

    int renewal(KindDto[] kindDtoList);

    String searchKind(String KNm);

}
