package com.project.poinhanshin.mapper.board;
import com.project.poinhanshin.domain.board.LikeBoardDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LikeMapper {

    // 좋아요 눌렀는지 체크
    int likeCheck(LikeBoardDto likeBoardDto);

    // 좋아요 테이블 추가
    int addLike(LikeBoardDto likeBoardDto);

    // 좋아요 테이블 삭제
    int deleteLike(LikeBoardDto likeBoardDto);

    // 게시판 likecount 값 업데이트
    int updateLikeCnt(Integer boardno , Integer num);

    // 게시판의 likecount 값을 가져온다.
    int searchLikeCnt(Integer boardno);

}
