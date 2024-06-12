package com.project.poinhanshin.mapper.protectboard;

import com.project.poinhanshin.domain.etc.SearchCondition;
import com.project.poinhanshin.domain.protectboard.ProtectBoardDto;
import org.apache.ibatis.session.SqlSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public class ProtectBoardMapperImpl implements ProtectBoardMapper{

    SqlSession sqlSession;

    @Autowired
    public ProtectBoardMapperImpl(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    private String namespace = "com.project.poinhanshin.mapper.protectboard.ProtectBoardMapper.";


    // 검색된 임보자 게시물 개수 반환
    @Override
    public int searchResultCnt(SearchCondition sc) {

        return sqlSession.selectOne(namespace+"searchResultCnt",sc);
    }

    // 검색된 임보자 게시물 리스트 반환
    @Override
    public List<ProtectBoardDto> searchResultList(SearchCondition sc) {
        return sqlSession.selectList(namespace+"searchResultList", sc);
    }


    // 특정 임보자 게시물 하나를 반환
    @Override
    public ProtectBoardDto selectContentOne(Integer protectboardno) {
        return sqlSession.selectOne(namespace+"selectContentOne", protectboardno);
    }

    // 임보자 게시물을 등록한다.
    @Override
    public int insertContent(ProtectBoardDto protectBoardDto) {
        return sqlSession.insert(namespace+"insertContent" , protectBoardDto);
    }

    // 임보자 게시물을 수정한다.
    @Override
    public int updateContent(ProtectBoardDto protectBoardDto , Integer loginUser) {
        HashMap hashMap = new HashMap();
        hashMap.put("protectBoardDto" , protectBoardDto);
        hashMap.put("loginUser" , loginUser);
        return sqlSession.update(namespace+"updateContent",hashMap);
    }

    // 임보자 게시물을 삭제한다.
    @Override
    public int deleteContent(Integer protectboardno, Integer loginUser) {
        HashMap<String , Integer> hashMap = new HashMap<>();
        hashMap.put("protectboardno" , protectboardno);
        hashMap.put("loginUser", loginUser );
        return sqlSession.delete(namespace+"deleteContent", hashMap);

    }

    // 최근에 사용자가 등록한 게시물 번호를 반환
    @Override
    public int selectRecentBoardno(Integer protectboard_userno) {
        return sqlSession.selectOne(namespace+"selectRecentBoardno" , protectboard_userno);
    }

    // 게시물의 파일 여부 값을 수정
    @Override
    public int updateFileAttached(Integer protectboardno, Integer fileAttached) {
        HashMap<String ,Integer> hashMap = new HashMap<>();
        hashMap.put("protectboardno" , protectboardno);
        hashMap.put("fileAttached" , fileAttached);
        return sqlSession.update(namespace+"updateFileAttached", hashMap);
    }


    //마이페이지 임보자 게시판 불러오기


    @Override
    public List<ProtectBoardDto> selectMyprotectboard(Integer userno) {
        return sqlSession.selectList(namespace +"selectMyprotectboard", userno);
    }

    @Override
    public List<ProtectBoardDto> writeMyprotectboard(Integer userno) {
        return sqlSession.selectList(namespace+"writeMyprotectboard", userno);
    }
}
