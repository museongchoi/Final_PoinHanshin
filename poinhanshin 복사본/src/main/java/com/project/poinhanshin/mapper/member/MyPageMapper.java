package com.project.poinhanshin.mapper.member;
import com.project.poinhanshin.domain.board.BoardDto;
import com.project.poinhanshin.domain.map.MapBoardDto;
import com.project.poinhanshin.domain.member.MyPageDto;
import com.project.poinhanshin.domain.member.User;
import com.project.poinhanshin.domain.protectboard.ProtectBoardDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MyPageMapper {
    void updateUser(User user);
    void dismissUser(Long userno);
    List<BoardDto> writeMyBoard(Integer userno);
    List<BoardDto> selectMyBoard(Integer userno);
    List<ProtectBoardDto> selectMyprotectboard(Integer userno);
    void favoriteMyProtectBoard(@Param("userno") Integer userno, @Param("protectBoardno") Integer protectBoardno);
    List<MapBoardDto> selectMyMap(Integer userno);

    // 즐겨찾기 게시물 확인
    int checkMyBoard(MyPageDto myPageDto);
    // 즐겨찾기 추가
    int insertFavorite(MyPageDto myPageDto);
    // 즐겨찾기 삭제
    int deleteFavorite(MyPageDto myPageDto);

}
