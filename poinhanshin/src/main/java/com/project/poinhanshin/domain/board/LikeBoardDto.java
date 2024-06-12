package com.project.poinhanshin.domain.board;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LikeBoardDto {
    private Integer likeboard_boardno;  // FK, Board 게시물 번호
    private Integer likeboard_userno;   // FK, User 회원 번호
    private Integer likeno;    // PK 좋아요 번호

    public LikeBoardDto(){}

    public LikeBoardDto(Integer likeboard_boardno, Integer likeboard_userno, Integer likeboardno) {
        this.likeboard_boardno = likeboard_boardno;
        this.likeboard_userno = likeboard_userno;
        this.likeno = likeboardno;
    }
}
