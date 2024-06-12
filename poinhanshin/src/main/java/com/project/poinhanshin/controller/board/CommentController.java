package com.project.poinhanshin.controller.board;

import com.project.poinhanshin.domain.board.CommentDto;
import com.project.poinhanshin.service.board.CommentService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentController {

    CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    // 댓글 읽어오기
    @GetMapping("/comments")
    @ResponseBody
    public ResponseEntity<List<CommentDto>> readCommentList( Integer boardcomment_boardno ){
        // 댓글 불러오기
        List<CommentDto> list = commentService.getCommentList(boardcomment_boardno);
        System.out.println(list);
        // 댓글 반환
        System.out.println("댓글목록 : "+list);
        return new ResponseEntity<List<CommentDto>>(list , HttpStatus.OK);
    }
    // 댓글 추가
    @PostMapping("/comments")
    @ResponseBody
    public ResponseEntity<String> addComment(@RequestBody CommentDto commentDto ){
        // 로그인이 안 된 경우 , CommentDto.userno()이 null임 그러므로 아무것도 실행 안 하게 함
        if(commentDto.getBoardcomment_userno() == null){
            System.out.println("");
            return new ResponseEntity<String>("로그인이 필요합니다.",HttpStatus.BAD_REQUEST);
        }
        System.out.println("등록 : "+commentDto);

        if(commentService.addComment(commentDto) != 1){
            // 댓글 등록 메서드 반환값이 0이라면 등록이 안 됐다는 의미
            return new ResponseEntity<String>("댓글 등록 실패" , HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<String>("댓글 등록 성공" , HttpStatus.OK);
        }
    }
    // 댓글 수정
    @PatchMapping("/comments")
    @ResponseBody
    public ResponseEntity<String> modifyComment(@RequestBody CommentDto commentDto , Integer loginId ){
        commentDto.setBoardcomment_userno(loginId);

        if(commentService.modifyComment(commentDto) != 1){
            return new ResponseEntity<String>("MODIFY_ERROR" ,HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<String>("MODIFY_OK" , HttpStatus.OK);
        }

    }


    // 댓글 삭제
    @DeleteMapping("/comments/{commentno}")
    @ResponseBody
    public ResponseEntity<String> removeComment(@PathVariable Integer commentno , Integer boardcomment_boardno , Integer loginUser) {

        if(loginUser == null)
            return new ResponseEntity<String>("로그인이 필요합니다." , HttpStatus.BAD_REQUEST);

        System.out.println("commentno = "+commentno);
        System.out.println("boardcomment_boardno = "+boardcomment_boardno);
        System.out.println("loginUser = "+loginUser);

        if(commentService.removeComment(boardcomment_boardno , commentno , loginUser) == 0){
            return new ResponseEntity<String>("댓글 삭제 불허" , HttpStatus.OK);
        }
        return new ResponseEntity<String>("댓글 삭제 허용" , HttpStatus.OK);
    }


}
