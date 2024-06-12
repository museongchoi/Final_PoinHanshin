package com.project.poinhanshin.service.mbti;

import com.project.poinhanshin.domain.mbti.MBTINameKind;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MBTIService {
    List<MBTINameKind> serviceSearchAband(Integer MBTI);
}
