package com.project.poinhanshin.controller.member;

import com.project.poinhanshin.domain.board.BoardDto;
import com.project.poinhanshin.domain.map.MapBoardDto;
import com.project.poinhanshin.domain.member.MyPageDto;

import com.project.poinhanshin.domain.member.User;
import com.project.poinhanshin.domain.protectboard.ProtectBoardDto;
import com.project.poinhanshin.service.member.MyPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MyPageController {

    MyPageService myPageService;
    @Autowired
    public MyPageController(MyPageService myPageService) {
        this.myPageService = myPageService;
    }

    @GetMapping("/myPage")
    public String myPage(@SessionAttribute(name = "loginUser", required = false) User loginUser, Model model) {
        model.addAttribute("loginUser", loginUser);
        return "mypage/mypage";
    }


    @GetMapping("/profilePage")
    public String profilePage(@SessionAttribute(name = "loginUser", required = false) User loginUser, Model model) {
        model.addAttribute("loginUser", loginUser);
        return "mypage/profile";
    }
    @GetMapping("/modifyPage")
    public String modifyPageForm(@SessionAttribute(name = "loginUser", required = false) User loginUser, Model model) {
        model.addAttribute("loginUser", loginUser);
//        return "members/myPageUpdate";
        return "mypage/profedit";
    }

    @PostMapping("/dismiss")
    public String dismiss(@SessionAttribute(name = "loginUser", required = false) User loginUser, Model model) {
        myPageService.dismiss(loginUser.getUserno());
        return "redirect:/logout";
    }

    @PostMapping("/modifyPage")
    public String modifyPage(User user) {
        myPageService.updateUser(user);
        return "redirect:/logout";
    }

    @GetMapping("/myWrittenPage")
    public String myWrittenPage(@SessionAttribute(name = "loginUser", required = false) User loginUser, Model model) {
        model.addAttribute("loginUser", loginUser);
        List<BoardDto> boardDtoList =  myPageService.writeMyBoard(Math.toIntExact(loginUser.getUserno()));
        model.addAttribute("boardDtoList", boardDtoList);
        return "mypage/myboard";
    }

    @GetMapping("/forumBookmark")
    public String forumBookmark(@SessionAttribute(name = "loginUser", required = false) User loginUser, Model model) {
        model.addAttribute("loginUser", loginUser);
        List<BoardDto> boardDtoList = myPageService.selectMyBoard(Math.toIntExact(loginUser.getUserno()));
        model.addAttribute("boardDtoList", boardDtoList);
        return "mypage/boardFav";
    }

    @GetMapping("/temporaryBookmark")
    public String temporaryBookmark(@SessionAttribute(name = "loginUser", required = false) User loginUser, Model model) {
        model.addAttribute("loginUser", loginUser);
        List<ProtectBoardDto> protectBoardDtoList = myPageService.selectMyprotectboard(Math.toIntExact(loginUser.getUserno()));
        model.addAttribute("protectBoardDtoList", protectBoardDtoList);
        return "mypage/secureFav";
    }

    @GetMapping("/mapBookmark")
    public String mapBookmark(@SessionAttribute(name = "loginUser", required = false) User loginUser, Model model) {
        model.addAttribute("loginUser", loginUser);
        List<MapBoardDto> mapBoardDtoList = myPageService.selectMyMap(Math.toIntExact(loginUser.getUserno()));
        model.addAttribute("mapBoardDtoList", mapBoardDtoList);
        return "mypage/findmapFav";
    }

    // 즐겨찾기
    @PostMapping("/favoriteUpdate")
    @ResponseBody
    public ResponseEntity<Integer> favoriteUpdate(@RequestBody MyPageDto myPageDto){

        System.out.println("favoriteUpdate : " +myPageDto );

        // 0 - 추가 , 1 - 삭제
        int checkResult = myPageService.favoriteFunc(myPageDto);

        return new ResponseEntity<Integer>(checkResult , HttpStatus.OK);
    }
}
