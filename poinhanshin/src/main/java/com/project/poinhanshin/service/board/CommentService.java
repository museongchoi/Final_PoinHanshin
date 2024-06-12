package com.project.poinhanshin.service.board;


import com.project.poinhanshin.domain.board.CommentDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.stream.events.Comment;
import java.util.List;

@Service
public interface CommentService {

    // 댓글 목록 가져오기
    List<CommentDto> getCommentList(Integer boardcomment_boardno);
    // 댓글 등록하기
    int addComment(CommentDto commentDto);
    // 댓글 수정하기
    int modifyComment(CommentDto commentDto);
    // 댓글 삭제하기
    int removeComment(Integer boardcomment_boardno , Integer commentno , Integer loginUser) ;

}
