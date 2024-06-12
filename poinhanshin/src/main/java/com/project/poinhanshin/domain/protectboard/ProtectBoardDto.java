package com.project.poinhanshin.domain.protectboard;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;
import java.util.TimeZone;


@Getter
@Setter
@ToString
@EqualsAndHashCode
public class ProtectBoardDto {
     private String protectboard_id;
     private Integer protectboard_userno;   // FK, 회원번호
     private Integer protectboardno;    // PK, 임보자공고 게시물 번호
     private String protectboard_title; // 임보자 게시물 제목
     private String protectboard_content;   // sqltype : varchar, 공고내용
     private String breeds;    // sqltype : varchar, 품종
     boolean protectboard_ani_category;  // 강아지 0 or 고양이 1
     @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
     private Date protectboard_reg_date;    // util.Date : date, 임보자 게시물 작성날짜
     boolean protectstatus;    // 공고중 확인, 공고중 0 or 공고완료 1


     @JsonFormat(pattern="yyyy-MM-dd HH:mm")
     /*@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")*/
     private Date starttime; // util.Date : date, 공고 시작일

     @JsonFormat(pattern="yyyy-MM-dd HH:mm")
     /*@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")*/
     private Date deadline; // util.Date : date, 마감날짜

     // 이미지 저장 관련 멤버변수
     // html -> Controller 파일 담는 용도
     private List<MultipartFile> protectboardFile = null;
     // 원본 파일 이름
     private List<String> originalFileName;
     // 서버 저장용 파일 이름
     private List<String> storedFileName;
     // 파일 첨부 여부(첨부 1,  미첨부 0) , boolean로 할 경우 엔티티(DB)에서 손이 많이 가게 됨
     private Integer fileAttached;

     public ProtectBoardDto(String protectboard_id, Integer protectboard_userno, Integer protectboardno, String protectboard_title, String protectboard_content, String breeds, boolean protectboard_ani_category, Date protectboard_reg_date, boolean protectstatus, Date starttime, Date deadline,  Integer fileAttached) {
          this.protectboard_id = protectboard_id;
          this.protectboard_userno = protectboard_userno;
          this.protectboardno = protectboardno;
          this.protectboard_title = protectboard_title;
          this.protectboard_content = protectboard_content;
          this.breeds = breeds;
          this.protectboard_ani_category = protectboard_ani_category;
          this.protectboard_reg_date = protectboard_reg_date;
          this.protectstatus = protectstatus;
          this.starttime = starttime;
          this.deadline = deadline;
          this.fileAttached = fileAttached;
     }
}




