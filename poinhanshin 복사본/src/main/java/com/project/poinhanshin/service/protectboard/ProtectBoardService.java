package com.project.poinhanshin.service.protectboard;

import com.project.poinhanshin.domain.etc.SearchCondition;
import com.project.poinhanshin.domain.protectboard.ProtectBoardDto;

import java.io.IOException;
import java.util.List;

public interface ProtectBoardService {


    // 검색된 임보자 게시물 개수를 반환
    int searchResultCnt(SearchCondition sc);

    // 검색된 임보자 게시물 리스트를 반환
    List<ProtectBoardDto> searchResultList(SearchCondition sc);

    // 특정 임보자 게시물 하나 가져오기
    ProtectBoardDto bringBoardOne(Integer protectboardno);

    // 임보자 게시물을 등록한다.
    int insertProductBoard(ProtectBoardDto protectBoardDto) throws IOException;

    // 임보자 게시물을 수정한다.
    int updateProductBoard(ProtectBoardDto protectBoardDto , Integer loginUser) throws IOException;

    // 임보자 게시물을 삭제한다.
    int deleteProductBoard(Integer protectboardno , Integer loginUser);


    // 최근 등록한 임보자 게시물의 번호를 반환
    int readWritedBoardno(Integer protectboard_userno);

    // 파일 존재 여부 값 수정
    int updateFileAttached(Integer protectboardno , Integer fileAttached);

    // 이미지 등록을 위한 메서드
    void addImgFiles(ProtectBoardDto protectBoardDto) throws IOException;

    //마이페이지 임보자 반환
    List<ProtectBoardDto> selectMyprotectboardService(Integer userno);

    //내가 쓴 글 가져오기
    List<ProtectBoardDto> writeMyprotectboardService(Integer userno);

}
