package com.project.poinhanshin.mapper.protectboard;

import com.project.poinhanshin.domain.etc.SearchCondition;
import com.project.poinhanshin.domain.protectboard.ProtectBoardDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProtectBoardMapper {

    // 검색된 임보자 게시물 개수 반환
    int searchResultCnt(SearchCondition sc);

    // 검색된 임보자 게시물 리스트 반환
    List<ProtectBoardDto> searchResultList(SearchCondition sc);

    // 특정 임보자 게시물 하나를 반환
    ProtectBoardDto selectContentOne(Integer protectboardno);

    // 임보자 게시물을 등록한다.
    int insertContent(ProtectBoardDto protectBoardDto);

    // 임보자 게시물을 수정한다.
    int updateContent(ProtectBoardDto protectBoardDto , Integer loginUser);

    // 임보자 게시물을 삭제한다.( 사용자 )
    int deleteContent(Integer protectboardno, Integer loginUser);

    // 최근 사용자가 등록한 게시물 번호를 반환.
    int selectRecentBoardno(Integer protectboardno);

    // 게시물의 파일 여부 값을 수정한다.
    int updateFileAttached(Integer protectboardno , Integer fileAttached);

    // 마이페이지 임보자 게시판 즐겨찾기
    List<ProtectBoardDto> selectMyprotectboard(Integer userno);

    // 마이페이지 내가 쓴 임보자 게시글 불러오기
    List<ProtectBoardDto> writeMyprotectboard(Integer userno);

}
