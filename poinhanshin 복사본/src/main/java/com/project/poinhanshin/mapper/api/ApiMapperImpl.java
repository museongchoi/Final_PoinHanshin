package com.project.poinhanshin.mapper.api;

import com.project.poinhanshin.domain.api.KindDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ApiMapperImpl implements ApiMapper{

    SqlSession sqlSession;

    @Autowired
    public ApiMapperImpl(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    String namespace = "com.project.poinhanshin.mapper.api.ApiMapper.";
    @Override
    public int deleteAll() {
        return sqlSession.delete(namespace+"deleteAll");
    }

    @Override
    public int insertData(KindDto kindDto) {
        return sqlSession.insert(namespace+"insertData", kindDto);
    }

    @Override
    public String selectKindCd(String KNm) {
        return sqlSession.selectOne(namespace+"selectKindCd" , KNm);
    }
}
