package com.project.poinhanshin.controller.protectboard;

import com.project.poinhanshin.domain.protectboard.ProtectBoardFileDto;
import com.project.poinhanshin.service.protectboard.ProtectBoardFileService;
import com.project.poinhanshin.service.protectboard.ProtectBoardServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Slf4j
@RestController
public class ProtectBoardFileController {


    ProtectBoardServiceImpl protectBoardService;
    ProtectBoardFileService protectBoardFileService;

    @Autowired
    public ProtectBoardFileController(ProtectBoardServiceImpl protectBoardService, ProtectBoardFileService protectBoardFileService) {
        this.protectBoardService = protectBoardService;
        this.protectBoardFileService = protectBoardFileService;
    }


    @PostMapping("/protectboard/uploadFile")
    @ResponseBody
    public int uploadFile(@RequestParam MultipartFile[] multipartFile, @RequestParam Integer protectboard_userno) throws IOException {
        System.out.println(Arrays.toString(multipartFile));
        System.out.println("uploadFile "+protectboard_userno);
        protectBoardFileService.insetFile(multipartFile , protectboard_userno);
        return 0;
    }
    // 첨부 파일 읽어오기
    @GetMapping("/protectboard/file")
    @ResponseBody
    public ResponseEntity<List<ProtectBoardFileDto>> read(Integer protectboardno) {
        // 특정 게시물의 첨부파일 데이터들을 읽어온다.
        List<ProtectBoardFileDto> protectBoardFileDtoList = protectBoardFileService.selectFiles(protectboardno);

        // 서버에 저장된 파일 이름들을 List로 반환
        return new ResponseEntity<List<ProtectBoardFileDto>>(protectBoardFileDtoList, HttpStatus.OK);
    }

    // 첨부 파일 삭제
    @DeleteMapping("/protectboard/remove")
    @ResponseBody
    public ResponseEntity<String> remove(@RequestBody String dataURL) {

        // 기존 사진을 제거한 경우
        if(dataURL.length() < 100){

            String stored_file_name = dataURL.substring(dataURL.indexOf("/protectboard/")+14 , dataURL.lastIndexOf('"'));
            System.out.println(stored_file_name);

            protectBoardFileService.deleteFile(stored_file_name);
            return new ResponseEntity<String>("Remove_existing_file", HttpStatus.OK);
        }
        return new ResponseEntity<String>("Remove_new_file", HttpStatus.OK);
    }
}
