package com.project.poinhanshin.mapper.map;

import com.project.poinhanshin.domain.map.MapBoardFileDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MapFileMapper {
    // 게시물에 해당하는 이미지들 가져오기
    List<MapBoardFileDto> MapBoardSelectFile(Integer mapboardno);

    // 게시물 이미지 등록한다
    int MapBoardInsertFile(MapBoardFileDto mapBoardFileDto);

    // 게시물 이미지 삭제
    int MapBoardDeleteFile(String stored_file_name);

    // 게시물 이미지 파일 개수 반환
    int MapBoardSelectCnt(Integer mapboardno);

    // 게시물 파일들 이름 반환
    List<String> MapBoardSelectFileName(Integer mapboardno);
}
