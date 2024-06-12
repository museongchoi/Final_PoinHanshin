package com.project.poinhanshin.service.map;

import com.project.poinhanshin.domain.etc.SearchCondition;
import com.project.poinhanshin.domain.map.MapBoardDto;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public interface MapBoardService {

    // 검색된 맵 게시물 목록을 반환
    List<MapBoardDto> getMapBoardListService(SearchCondition sc);

    // 검색된 맵 게시물 목록 개수를 반환
    int getMapBoardListCnt(SearchCondition sc);

    // 특정 맵 게시물 하나 반환
    MapBoardDto bringMapBoardOne(Integer mapboardno);

    // 맵 게시물 등록
    int writeMapBoard(MapBoardDto mapBoardDto) throws IOException;

    // 맵 게시물 수정
    int modifyMapBoard(MapBoardDto mapBoardDto , Integer loginUser) throws IOException;

    // 맵 게시물 삭제
    int removeMapBoard(Integer mapboardno , Integer loginUser) throws IOException;

    // 최근 등록 게시물 번호 반환
    int bringRecentContentNo(Integer mapboard_userno);

    // 파일 존재 여부 값 수정
    int updateFileAttached(Integer mapboardno , Integer fileAttached);

    // 이미지 등록을 위한 메서드
    void addImgFiles(MapBoardDto mapBoardDto) throws IOException;

    ////////////////////////////////////////////////

    // 마이페이지 - 자신의 게시판 즐겨찾기 리스트 불러오기
    List<MapBoardDto> SelectMyMapService(Integer userno);

    // 마이페이지 - 자신이 작성한 실종/발견 지도 게시글 불러오기
    List<MapBoardDto> WriteMyMapService(Integer userno);

    List<MapBoardDto> selectMapList(SearchCondition sc);

}
