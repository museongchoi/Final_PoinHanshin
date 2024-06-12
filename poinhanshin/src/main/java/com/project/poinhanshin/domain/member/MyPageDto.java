package com.project.poinhanshin.domain.member;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
// 마이페이지_즐겨찾기 확인 & 개인정보 확인 페이지
public class MyPageDto {
     private Integer mypage_userno; // FK, 회원 번호
     private Integer mypage_boardno;    // FK, 게시판 게시물 번호
     private Integer mypage_protectboardno; // FK, 임보자공고 게시물 번호
     private Integer mypage_mapboardno; // FK, 실종/발견지도 게시물 번호
     private Integer favorites; // PK, 즐겨찾기 번호

      /* MyPage table
       1/이재성/b13/null/null
       2/이재성/b14/null/null
       3/이재우/null/p13/null
       4/이재우/null/null/m13
      */

     public MyPageDto(Integer mypage_userno, Integer mypage_boardno, Integer mypage_protectboardno, Integer mypage_mapboardno, Integer favorites) {
          this.mypage_userno = mypage_userno;
          this.mypage_boardno = mypage_boardno;
          this.mypage_protectboardno = mypage_protectboardno;
          this.mypage_mapboardno = mypage_mapboardno;
          this.favorites = favorites;
     }
}
