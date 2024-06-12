package com.project.poinhanshin.controller.board;

import com.project.poinhanshin.domain.board.BoardFileDto;
import com.project.poinhanshin.service.board.BoardFileService;
import com.project.poinhanshin.service.board.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Conditional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BoardFileController {

    BoardService boardService;
    BoardFileService boardFileService;

    @Autowired
    public BoardFileController(BoardService boardService, BoardFileService boardFileService) {
        this.boardService = boardService;
        this.boardFileService = boardFileService;
    }

    // 게시물의 첨부 파일 읽어오기
    @GetMapping("/board/file")
    @ResponseBody
    public ResponseEntity<List<BoardFileDto>> readFile(Integer board_boardno){

        // 특정 게시물의 첨부 파일 데이터들을 읽어온다.
        List<BoardFileDto> boardFileDtoList = boardFileService.boardSelectFile(board_boardno);

        System.out.println(boardFileDtoList);
        // 서버에 저장된 파일 이름들을 List로 반환
        return new ResponseEntity<List<BoardFileDto>>(boardFileDtoList , HttpStatus.OK);
    }

    // 게시물의 첨부 파일 삭제
    @DeleteMapping("/board/remove")
    @ResponseBody
    public ResponseEntity<String> removeFile(@RequestBody String dataURL){

        System.out.println(dataURL);

        // 기존 사진을 제거한 경우
        if(dataURL.length() < 100){
            String stored_file_name = dataURL.substring(dataURL.indexOf("/board/")+7 , dataURL.lastIndexOf('"'));
            System.out.println(stored_file_name);
            boardFileService.boardDeleteFile(stored_file_name);
            return new ResponseEntity<String>("Remove_existing_file", HttpStatus.OK);
        }
        return new ResponseEntity<String>("Remove_new_file", HttpStatus.OK);
    }
}
