package com.project.poinhanshin.domain.board;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class BoardDto {
        private String id;      // users 테이블의 회원 String id
        private Integer board_userno;   // 게시판 회원 번호 FK
        private Integer boardno;        // 게시판 번호 PK
        private String board_title;     // 제목
        private String board_content;   // 내용
        private boolean board_ani_category;     // 강아지 고양이 필터
        @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private Date board_reg_date;    // 글 작성 날짜 + 시간
        private Integer board_viewcount;        // 조회 수
        private Integer commentcount;   // 댓글 수
        private Integer likecount;      // 좋아요 수

        // 이미지 저장 관련 멥버 변수
        // html -> Controller 파일 담는 용도
        private List<MultipartFile> boardFile = null;

        // 원본 파일 이름
        private List<String> originalFileName;

        // 서버 저장용 파일 이름
        private List<String> storedFileName;

        //파일 첨부 여부(첨부 1, 미첨부 0) , boolean 할 경우 엔티티(DB)에서 손이 많이 감
        private Integer fileAttached;

        public BoardDto(String id, Integer board_userno, Integer boardno, String board_title, String board_content, boolean board_ani_category, Date board_reg_date, Integer board_viewcount, Integer commentcount, Integer likecount, Integer fileAttached) {
                this.id = id;
                this.board_userno = board_userno;
                this.boardno = boardno;
                this.board_title = board_title;
                this.board_content = board_content;
                this.board_ani_category = board_ani_category;
                this.board_reg_date = board_reg_date;
                this.board_viewcount = board_viewcount;
                this.commentcount = commentcount;
                this.likecount = likecount;
                this.fileAttached = fileAttached;
        }
}
