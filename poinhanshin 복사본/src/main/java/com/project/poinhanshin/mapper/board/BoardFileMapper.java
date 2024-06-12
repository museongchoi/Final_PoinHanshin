package com.project.poinhanshin.mapper.board;

import com.project.poinhanshin.domain.board.BoardFileDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardFileMapper {

    // 게시물에 해당하는 이미지들 가져온다.
    List<BoardFileDto> boardSelectFile(Integer boardno);

    // 게시물 이미지를 등록한다.
    int boardInsertFile(BoardFileDto boardFileDto);

    // 게시물 이미지를 삭제한다.
    int boardDeleteFile(String stored_file_name);

    // 게시물 이미지 파일 개수 반환
    int boardSelectCnt(Integer boardno);

    // 게시물 파일들 이름 반환
    List<String> boardSelectFileName(Integer boardno);


}
