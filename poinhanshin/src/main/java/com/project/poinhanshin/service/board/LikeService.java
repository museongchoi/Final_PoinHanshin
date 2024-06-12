package com.project.poinhanshin.service.board;

import com.project.poinhanshin.domain.board.LikeBoardDto;

import java.util.HashMap;

public interface LikeService {

    /*String checkLike(Integer userNo , Integer bno) throws Exception;

    String nonCheckLike(Integer userNo , Integer bno) throws Exception;*/
    // 좋아요 눌렀는지 확인 ( 테이블 있으면 누른 것 , 없으면 안 누른 것
    Integer likeCheck(LikeBoardDto likeBoardDto);

    // 게시판의 좋아요 개수 반환
    Integer searchLikeCnt(Integer boardno);

    // 좋아요 기능
    Integer likeFunction(LikeBoardDto likeBoardDto) throws Exception;
}
