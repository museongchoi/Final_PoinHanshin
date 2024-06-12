package com.project.poinhanshin.service.board;

import com.project.poinhanshin.domain.board.BoardDto;
import com.project.poinhanshin.domain.etc.SearchCondition;
import com.project.poinhanshin.domain.protectboard.ProtectBoardDto;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@Service
public interface BoardService {

    // 검색된 게시물 리스트 개수를 가져온다.
    int bringBoarndListCnt(SearchCondition sc);

    // 검색된 게시물 리스트를 가져온다.
    HashMap bringBoardList(SearchCondition sc);

    // 특정 게시물 하나 가져오기
    BoardDto bringBoardOne(Integer boardno);

    // 게시물을 등록한다.
    int writeContent(BoardDto boardDto) throws IOException;

    // 특정 게시물을 수정한다.
    int modifyContent(BoardDto boardDto , Integer loginUser) throws IOException;

    // 특정 게시물을 삭제한다.
    int removeContent(Integer boardno , Integer loginUser) throws IOException;

    // 최근 등록 게시물 번호를 가져온다.(매개변수 유저에 해당하는 것을 가져오는 것)
    int bringRecentRegContentNo(Integer board_userno);

    // 파일 존재 여부 값 수정
    int updateFileAttached(Integer boardno , Integer fileAttached);

    // 이미지 등록을 위한 메서드
    void addImgFiles(BoardDto boardDto) throws IOException;

    // 마이페이지 - 자신의 게시판 즐겨찾기 리스트 불러오기
    List<BoardDto> SelectMyBoardService(Integer userno);

    // 마이페이지 - 자신이 쓴 커뮤니티 게시글 불러오기
    List<BoardDto> WriteMyBoardService(Integer userno);

}
