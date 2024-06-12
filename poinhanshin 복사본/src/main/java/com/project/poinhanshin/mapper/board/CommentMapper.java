package com.project.poinhanshin.mapper.board;

import com.project.poinhanshin.domain.board.CommentDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommentMapper {

    // 특정 게시물에 대한 댓글 목록을 가져온다.
    List<CommentDto> selectComment(Integer boardcomment_boardno);

    // 댓글 등록
    int insertContent(CommentDto commentDto);

    // 댓글 수정
    int updateComment(CommentDto commentDto);

    // 댓글 삭제
    int deleteComment(Integer commentno , Integer loginUser );
}
