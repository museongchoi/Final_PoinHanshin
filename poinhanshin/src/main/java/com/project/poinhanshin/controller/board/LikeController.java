package com.project.poinhanshin.controller.board;

import com.project.poinhanshin.domain.board.LikeBoardDto;
import com.project.poinhanshin.service.board.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class LikeController {

    LikeService likeService;

    @Autowired
    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    /*@GetMapping("/likeTest")
    public String likeTest(Model m){
        likeService.likeFunction(1, 1);
        return "test/likeDBTest";
    }*/

    // 좋아요 체크
    @GetMapping("/likeCheck")
    @ResponseBody
    public ResponseEntity<Integer> likeCheck(LikeBoardDto likeBoardDto){
        System.out.println("lickCheck - "+likeBoardDto);
        Integer checkNum = likeService.likeCheck(likeBoardDto);
        System.out.println(checkNum);
        return new ResponseEntity<Integer>(checkNum , HttpStatus.OK);
    }

    // 좋아여 기능 구현
    @PostMapping("/likeUpdate")
    @ResponseBody
    public Map<String , Integer> likeUpdte(@RequestBody LikeBoardDto likeBoardDto) throws Exception {

        // 테이블 검색한 결과 반환
        Integer checkNum = likeService.likeFunction(likeBoardDto);

        // 게시판 좋아요 개수 반환
        Integer likeCnt = likeService.searchLikeCnt(likeBoardDto.getLikeboard_boardno());

        System.out.println("likeCnt - "+likeCnt);
        // 프론트에서 쉽게 사용하도록 숫자 변환
        checkNum = checkNum == 0 ? 1 : 0;
        System.out.println(checkNum);

        Map<String , Integer> map = new HashMap<String , Integer>();
        map.put("checkNum", checkNum);
        map.put("likeCnt", likeCnt);

       return map;
    }



}
