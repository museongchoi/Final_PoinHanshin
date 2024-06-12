package com.project.poinhanshin.service.board;

import com.project.poinhanshin.domain.board.BoardFileDto;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public interface BoardFileService {

    // 이미지 불러오기
    List<BoardFileDto> boardSelectFile(Integer boardno);

    // 이미지 삭제
    int boardDeleteFile(String stored_file_name);

    // 이미지 개수 카운트
    int boardSelectCnt(Integer boardFileno);

    // 이미지 저장
    int boardInsertFile(MultipartFile[] multipartFile , Integer board_userno) throws IOException;
}
