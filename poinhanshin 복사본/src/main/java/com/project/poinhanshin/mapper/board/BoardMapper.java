package com.project.poinhanshin.mapper.board;

import com.project.poinhanshin.domain.board.BoardDto;
import com.project.poinhanshin.domain.etc.SearchCondition;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface BoardMapper {

    // 검색된 게시물 개수 반환
    int boardSearchResultCnt(SearchCondition sc);

    // 검색된 게시물 리스트 반환
    List<BoardDto> boardSearchResultList(SearchCondition sc);

    // 특정 게시물 하나를 읽어온다.
    BoardDto boardSelectContentOne(Integer boardno);

    // 게시물을 등록한다.
    int boardInsertContent(BoardDto boardDto);

    // 게시물을 수정한다.
    int boardUpdateContent(BoardDto boardDto , Integer loginUser);

    // 게시물을 삭제한다.
    int boardDeleteContent(Integer boardno , Integer loginUser);

    // 최근 사용자가 등록한 게시물의 번호를 가져온다. (즐겨찾기?)
    int boardSelectRecentBoardNo(Integer board_userno);

    // 게시물의 파일 여부 값을 수정한다.
    int boardUpdateFileAttached(Integer boardno , Integer fileAttached);

    // 조회수 업데이트
    int updateViewCnt(Integer boardno);

    // 조회수 Top3
    List<BoardDto> selectViewCntTop();

    // 좋아요 수 Top 3
    List<BoardDto> selectLikeCntTop();

    // 댓글 개수 업데이트
    int updateCommentCnt(@Param("boardno")Integer boardno, @Param("num")Integer num);

    // 마이페이지 - 자신의 게시판 즐겨찾기 리스트 불러오기
    List<BoardDto> SelectMyBoard(Integer userno);

    // 마이페이지 - 자신이 쓴 커뮤니티 게시글 불러오기
    List<BoardDto> WriteMyBoard(Integer userno);

}
