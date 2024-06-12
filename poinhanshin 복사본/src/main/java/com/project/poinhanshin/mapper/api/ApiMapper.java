package com.project.poinhanshin.mapper.api;

import com.project.poinhanshin.domain.api.KindDto;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ApiMapper {

    int deleteAll();

    int insertData(KindDto kindDto);

    String selectKindCd(String KNm);
}
