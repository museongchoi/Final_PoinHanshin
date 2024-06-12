package com.project.poinhanshin.mapper.board;

import com.project.poinhanshin.domain.board.BoardDto;
import com.project.poinhanshin.domain.etc.SearchCondition;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public class BoardMapperImpl implements BoardMapper {

    SqlSession sqlSession;

    @Autowired
    public BoardMapperImpl(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    private String namespace ="com.project.poinhanshin.mapper.board.BoardMapper.";

    // 검색된 게시물 개수 반환
    @Override
    public int boardSearchResultCnt(SearchCondition sc) {
        return sqlSession.selectOne(namespace+"boardSearchResultCnt", sc);
    }

    // 검색된 게시물 리스트 반환
    @Override
    public List<BoardDto> boardSearchResultList(SearchCondition sc) {
        return sqlSession.selectList(namespace+"boardSearchResultList",sc);
    }
    // 특정 게시물 하나를 읽어온다.
    @Override
    public BoardDto boardSelectContentOne(Integer boardno) {
        return sqlSession.selectOne(namespace+"boardSelectContentOne", boardno);
    }

    // 게시물을 등록한다.
    @Override
    public int boardInsertContent(BoardDto boardDto) {
        return sqlSession.insert(namespace+"boardInsertContent",boardDto);
    }

    // 게시물을 수정한다.
    @Override
    public int boardUpdateContent(BoardDto boardDto, Integer loginUser) {
        HashMap hashMap = new HashMap();
        hashMap.put("boardDto",boardDto);
        hashMap.put("loginUser" ,loginUser);
        return sqlSession.update(namespace+"boardUpdateContent",hashMap);
    }

    // 게시물을 삭제한다.
    @Override
    public int boardDeleteContent(Integer boardno, Integer loginUser) {
        HashMap hashMap = new HashMap();
        hashMap.put("boardno" , boardno);
        hashMap.put("loginUser",loginUser);
        return sqlSession.delete(namespace+"boardDeleteContent",hashMap);
    }

    // 최근 등록한 게시물의 번호를 가져온다.
    @Override
    public int boardSelectRecentBoardNo(Integer board_userno) {
        return sqlSession.selectOne(namespace+"boardSelectRecentBoardNo" , board_userno);
    }

    // 게시물의 파일 여부 값을 수정한다.
    @Override
    public int boardUpdateFileAttached(Integer boardno , Integer fileAttached) {
        HashMap<String ,Integer> hashMap = new HashMap<>();
        hashMap.put("boardno" , boardno);
        hashMap.put("fileAttached" , fileAttached);
        return sqlSession.update(namespace+"boardUpdateFileAttached", hashMap);
    }

    // 조회수 업데이트 기능
    @Override
    public int updateViewCnt(Integer boardno) {
        return sqlSession.update(namespace+"updateViewCnt" , boardno);
    }

    // 게시판 목록 검색 Top3
    @Override
    public List<BoardDto> selectViewCntTop() {
        return sqlSession.selectList(namespace+"selectViewCntTop");
    }

    @Override
    public List<BoardDto> selectLikeCntTop() {
        return sqlSession.selectList(namespace+"selectLikeCntTop");
    }

    @Override
    public int updateCommentCnt(Integer boardno, Integer num) {
        HashMap hashMap = new HashMap();
        hashMap.put("boardno",boardno);
        hashMap.put("num" , num);
        return sqlSession.update(namespace+"updateCommentCnt" , hashMap);
    }

    // 댓글 개수 수정

    // 마이페이지 - 자신의 게시판 즐겨찾기 리스트 불러오기
    @Override
    public List<BoardDto> SelectMyBoard(Integer userno) {
        return sqlSession.selectList(namespace+"SelectMyBoard", userno);
    }

    // 마이페이지 - 자신이 쓴 커뮤니티 게시글 불러오기
    @Override
    public List<BoardDto> WriteMyBoard(Integer userno) {
        return sqlSession.selectList(namespace+"WriteMyBoard",userno);
    }
}
