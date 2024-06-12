package com.project.poinhanshin.service.protectboard;

import com.project.poinhanshin.domain.protectboard.ProtectBoardFileDto;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public interface ProtectBoardFileService {

    // 이미지 불러오기
    List<ProtectBoardFileDto> selectFiles(Integer protectboardno);

    // 이미지 삭제
    int deleteFile(String stored_file_name );

    // 게시물 이미지 개수 카운트
    int selectCnt(Integer protectboardfileno);

    // 이미지 저장
    int insetFile(MultipartFile[] multipartFile, Integer protectboard_userno) throws IOException;
}
