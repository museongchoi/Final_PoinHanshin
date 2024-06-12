package com.project.poinhanshin.mapper.map;

import com.project.poinhanshin.domain.etc.SearchCondition;
import com.project.poinhanshin.domain.map.MapBoardDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MapMapper {

    // 검색된 맵 게시물 수를 가져온다.
    int selectMapCnt(SearchCondition sc);

    // 검색된 맵 게시물 리스트를 가져온다.
    List<MapBoardDto> selectMapList(SearchCondition sc);

    // 하나의 맵 게시물 정보를 가져온다.
    MapBoardDto selectMapBoardOne(Integer mapboardno);

    // 맵 게시물 등록
    int insertMapBoard(MapBoardDto mapBoardDto);

    // 맵 게시물 수정
    int updateMapBoard(MapBoardDto mapBoardDto , Integer loginUser);

    // 맵 게시물 삭제
    int deleteMapBoard(Integer mapboardno  , Integer loginUser);

    // 최근 맵 게시물 번호를 반환
    int selectRecentMapBoardNo(Integer mapboard_userno);

    // 파일 존재 여부 값 수정
    int mapBoardUpdateFileAttached(Integer mapboardno , Integer fileAttached);

    // 마이페이지 - 자신의 게시판 즐겨찾기 리스트 불러오기
    List<MapBoardDto> SelectMyMap(Integer userno);

    // 마이페이지 - 자신이 작성한 실종/발견 지도 게시글 불러오기
    List<MapBoardDto> WriteMyMap(Integer userno);

}
