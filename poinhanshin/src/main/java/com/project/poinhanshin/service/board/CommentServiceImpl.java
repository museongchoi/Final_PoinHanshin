package com.project.poinhanshin.service.board;

import com.project.poinhanshin.domain.board.CommentDto;
import com.project.poinhanshin.mapper.board.BoardMapper;
import com.project.poinhanshin.mapper.board.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;


@Service
public class CommentServiceImpl implements CommentService {

    CommentMapper commentMapper;
    BoardMapper boardMapper;

    // 의존성 추가
    @Autowired
    public CommentServiceImpl(CommentMapper commentMapper, BoardMapper boardMapper) {
        this.commentMapper = commentMapper;
        this.boardMapper = boardMapper;
    }

    // 댓글 목록을 가져온다.
    @Override
    public List<CommentDto> getCommentList(Integer boardcomment_boardno) {
        return commentMapper.selectComment(boardcomment_boardno);
    }

    // 댓글 추가
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int addComment(CommentDto commentDto) {
        // 값을 증가시켜줘야하기 때문
        boardMapper.updateCommentCnt(commentDto.getBoardcomment_boardno() , 1);
        return commentMapper.insertContent(commentDto);
    }

    // 댓글 수정
    @Override
    public int modifyComment(CommentDto commentDto) {
        return commentMapper.updateComment(commentDto);
    }

    // 댓글 삭제
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int removeComment(Integer boardcomment_boardno, Integer commentno, Integer loginUser)  {

        int result = commentMapper.deleteComment(commentno , loginUser);

        // result가 0이 아니라는 것은 댓글을 삭제했다는 의미 ( 영향을 준 테이블 개수를 반환하기 때문 )
        System.out.println("result = " + result);
        if(result != 0){
            // 삭제 처리 됨, 영향을 준 만큼 개수를 감소
            boardMapper.updateCommentCnt(boardcomment_boardno ,  -result);
        }


        return result;
    }
}
