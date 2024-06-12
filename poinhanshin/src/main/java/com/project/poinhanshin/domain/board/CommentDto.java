package com.project.poinhanshin.domain.board;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;


import java.util.Date;


@Getter
@Setter
@ToString
@EqualsAndHashCode
public class CommentDto {


   private String id;
   private Integer boardcomment_boardno;
   private Integer boardcomment_userno;
   private Integer commentno;
   private Integer parcommentno;
   private String comment;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    /*@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")*/
   private Date boardcomment_reg_date;

   public CommentDto(String id,Integer boardcomment_boardno, Integer boardcomment_userno, Integer commentno, Integer parcommentno, String comment, Date boardcomment_reg_date) {
      this.id = id;
      this.boardcomment_boardno = boardcomment_boardno;
      this.boardcomment_userno = boardcomment_userno;
      this.commentno = commentno;
      this.parcommentno = parcommentno;
      this.comment = comment;
      this.boardcomment_reg_date = boardcomment_reg_date;
   }
}


