package com.project.poinhanshin.mapper.protectboard;

import com.project.poinhanshin.domain.protectboard.ProtectBoardFileDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProtectBoardFileMapper {

    // 임보자 게시물에 해당하는 이미지들을 가져오는 메서드
    List<ProtectBoardFileDto> selectFiles(Integer protectboardno);

    // 이미지 파일 등록
    int insertFiles(ProtectBoardFileDto protectBoardFileDto);

    // 이미지 파일 삭제
    int deleteFile(String stored_file_name );

    // 게시물 이미지 파일 개수 반환
    int selectCnt(Integer protectboardno);

    // 게시물의 파일들 이름 반환
    List<String> selectFilesName(Integer protectboardno);

}
