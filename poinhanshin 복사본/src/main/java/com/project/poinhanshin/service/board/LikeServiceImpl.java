package com.project.poinhanshin.service.board;

import com.project.poinhanshin.domain.board.LikeBoardDto;
import com.project.poinhanshin.mapper.board.LikeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LikeServiceImpl implements LikeService {

    LikeMapper likeMapperImpl;


    @Autowired
    public LikeServiceImpl(LikeMapper likeMapperImpl) {
        this.likeMapperImpl = likeMapperImpl;
    }


    @Override
    public Integer likeCheck(LikeBoardDto likeBoardDto) {
        return likeMapperImpl.likeCheck(likeBoardDto);
    }

    @Override
    public Integer searchLikeCnt(Integer boardno) {
        return likeMapperImpl.searchLikeCnt(boardno);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer likeFunction(LikeBoardDto likeBoardDto) throws Exception {
        Integer result = likeMapperImpl.likeCheck(likeBoardDto);
        // 해당 게시물에서 좋아요를 눌렀는지 확인
        if(result == 0){
            // 이전에 좋아요 버튼을 누른 적 없음

            // 좋아요 테이블 추가
            likeMapperImpl.addLike(likeBoardDto);
            // 게시판의 좋아요 값 증가
            likeMapperImpl.updateLikeCnt(likeBoardDto.getLikeboard_boardno() , 1);

        } else if( result == 1){
            // 이전에 좋아요 버튼을 누름

            // 좋아요 테이블 삭제
            likeMapperImpl.deleteLike(likeBoardDto);

            // 게시판의 좋아요 값 감소
            likeMapperImpl.updateLikeCnt(likeBoardDto.getLikeboard_boardno() , -1);
        }

        return result;
    }







   /* @Transactional(rollbackFor = Exception.class)
    @Override
    public String likeFunction(Integer userNo, Integer bno) {
        // 메시지 담을 변수
        String msg = "";

        int check = likeMapperImpl.likeCheck(userNo , bno);
        if(check != 1){
            // likeboard 테이블에 튜플 추가
            likeMapperImpl.addLike(userNo , bno);
            // borad 테이블의 likecount 속성의 값을 1증가시킨다.
            likeMapperImpl.updateLikeCnt(bno , 1);
            msg = "좋아요 버튼을 활성화 합니다.";
        }else{
            // likeboard 테이블에서 튜플 제거
            likeMapperImpl.deleteLike(userNo , bno);
            // board 테이블의 likecount 속성 값을 1 감소시킨다.
            likeMapperImpl.updateLikeCnt(bno,-1);

            msg = "좋아요 버튼을 비활성화 합니다.";
        }
        return msg;
    }*/

    /*@Transactional(rollbackFor = Exception.class)
    @Override
    public String checkLike(Integer userNo, Integer bno) throws Exception {
        // 좋아요 버튼이 있는지 확인
        int check = likeDaoImpl.likeCheck(userNo , bno);
        if(check == 1)
            throw new Exception("이미 Like 테이블이 존재합니다.");
        // likeboard 테이블에 튜플 추가
        likeDaoImpl.addLike(userNo , bno);
        // borad 테이블의 likecount 속성의 값을 1증가시킨다.
        likeDaoImpl.LikeCntUp(bno);
        return "좋아요 버튼을 활성화합니다..";
    }x

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String nonCheckLike(Integer userNo, Integer bno) throws Exception {
        // 좋아요 버튼이 있는지 확인
        int check = likeDaoImpl.likeCheck(userNo , bno);
        if(check == 1)
            throw new Exception("Like 테이블이 존재하지 않습니다.");
        // likeboard 테이블에서 튜플 제거
        likeDaoImpl.deleteLike(userNo , bno);
        // board 테이블의 likecount 속성 값을 1 감소시킨다.
        likeDaoImpl.LikeCntDown(bno);


        return "좋아요 버튼을 비활성화합니다..";
    }*/



}
