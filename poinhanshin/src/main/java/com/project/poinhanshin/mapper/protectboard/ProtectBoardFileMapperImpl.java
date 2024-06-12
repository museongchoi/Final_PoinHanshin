package com.project.poinhanshin.mapper.protectboard;

import com.project.poinhanshin.domain.protectboard.ProtectBoardFileDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProtectBoardFileMapperImpl implements ProtectBoardFileMapper{

    SqlSession sqlSession;

    // 의존성 주입
    @Autowired
    public ProtectBoardFileMapperImpl(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    // namespace 정의
    private String namespace = "com.project.poinhanshin.mapper.protectboard.ProtectBoardFileMapper.";

    // 임보자 게시물에 해당하는 이미지들 반환
    @Override
    public List<ProtectBoardFileDto> selectFiles(Integer protectboardno) {
        return sqlSession.selectOne(namespace+"selectFiles" , protectboardno);
    }

    // 이미지 파일을 등록
    @Override
    public int insertFiles(ProtectBoardFileDto protectBoardFileDto) {
        return sqlSession.insert(namespace+"insertFiles" , protectBoardFileDto);
    }

    // 이미지 파일을 삭제
    @Override
    public int deleteFile(String stored_file_name) {
        return sqlSession.delete(namespace+"deleteFile",stored_file_name );
    }

    // 게시물 이미지 파일 개수 반환
    @Override
    public int selectCnt(Integer protectboardno) {
        return sqlSession.selectOne(namespace+"selectCnt",protectboardno );
    }

    // 게시물의 파일들 이름 반환
    @Override
    public List<String> selectFilesName(Integer protectboardno) {
        return sqlSession.selectList(namespace+"selectFilesName",protectboardno);
    }
}
