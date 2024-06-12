package com.project.poinhanshin.service.member;

import com.project.poinhanshin.domain.board.BoardDto;
import com.project.poinhanshin.domain.map.MapBoardDto;
import com.project.poinhanshin.domain.member.MyPageDto;
import com.project.poinhanshin.domain.member.User;
import com.project.poinhanshin.domain.protectboard.ProtectBoardDto;
import com.project.poinhanshin.mapper.member.MyPageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MyPageService {
    MyPageMapper myPageMapper;
    @Autowired
    public MyPageService(MyPageMapper myPageMapper) {
        this.myPageMapper = myPageMapper;
    }
    public void updateUser(User user) {
        myPageMapper.updateUser(user);
    }
    public void dismiss(Long userno) {
        myPageMapper.dismissUser(userno);
    }

    public List<BoardDto> writeMyBoard(Integer userno) {
        return myPageMapper.writeMyBoard(userno);
    }
    public List<BoardDto> selectMyBoard(Integer userno) {
        return myPageMapper.selectMyBoard(userno);
    }
    public List<ProtectBoardDto> selectMyprotectboard(Integer userno) {
        return myPageMapper.selectMyprotectboard(userno);
    }
    public void favoriteMyProtectBoard(Integer userno, Integer protectBoardno) {
        myPageMapper.favoriteMyProtectBoard(userno, protectBoardno);
    }
    public List<MapBoardDto> selectMyMap(Integer userno) {
        return myPageMapper.selectMyMap(userno);
    }


    // 즐겨찾기 여부 확인 후 값 변경

    @Transactional(rollbackFor = Exception.class)
    public int favoriteFunc(MyPageDto myPageDto){

        // DB에 저장되어 있는지 확인
        int checkResult = myPageMapper.checkMyBoard(myPageDto);
        int result;

        if(checkResult != 0){
            // 데이터가 있다는 의미
            result = myPageMapper.deleteFavorite(myPageDto);
        } else {
            result = myPageMapper.insertFavorite(myPageDto);
        }

        // result가 1이면 제대로 작동한 것
        return checkResult;
    }

}
