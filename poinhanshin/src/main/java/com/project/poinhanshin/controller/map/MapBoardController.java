package com.project.poinhanshin.controller.map;

import com.project.poinhanshin.domain.etc.PageHandler;
import com.project.poinhanshin.domain.etc.SearchCondition;
import com.project.poinhanshin.domain.map.MapBoardDto;
import com.project.poinhanshin.domain.member.User;
import com.project.poinhanshin.service.map.MapBoardService;
import com.project.poinhanshin.service.member.MyPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/map")
public class MapBoardController {

    MapBoardService mapBoardService;
    MyPageService myPageService;

    @Autowired
    public MapBoardController(MapBoardService mapBoardService, MyPageService myPageService) {
        this.mapBoardService = mapBoardService;
        this.myPageService = myPageService;
    }

    // 메인 페이지 호출
    @GetMapping("/main")
    public String goToMapMain(@SessionAttribute(name = "loginUser", required = false) User loginUser, Model model){
        List<MapBoardDto> mapBoardDtoList = myPageService.selectMyMap(Math.toIntExact(loginUser.getUserno()));
        model.addAttribute("mapBoardDtoList", mapBoardDtoList);
        return "/map/findmapmainonemain";
    }

    // 맵 게시물 목록 불러오기
    @GetMapping("/list")
    public String getMapBoardList(SearchCondition sc , Model m , @ModelAttribute("msg") String msg ,  @SessionAttribute(name = "loginUser", required = false) User loginUser ){

        System.out.println("map list sc : " + sc);

        sc.setPageSize(6);
        // 로그인 여부
        if(loginUser != null){
            // 로그인 정보 전달
            m.addAttribute("loginUser" , loginUser);
        }

        // 필터에 검색된 게시물 목록 반환
        List<MapBoardDto> mapBoardDtoList = mapBoardService.getMapBoardListService(sc);

        System.out.println(mapBoardDtoList);

        int totalCnt = mapBoardService.getMapBoardListCnt(sc);

        PageHandler ph = new PageHandler(totalCnt , sc);

        m.addAttribute("mapBoardDtoList",mapBoardDtoList);
        m.addAttribute("totalCnt" , totalCnt);
        m.addAttribute("sc", sc);
        m.addAttribute("ph" ,ph);
        m.addAttribute("msg" , msg);
        return "/map/findmaplist";
    }

    // 맵 보드 게시물 상세 페이지
    @GetMapping("/read")
    public String getMapBoardOne(Integer mapboardno , SearchCondition sc, Model m  , @SessionAttribute(name = "loginUser", required = false) User loginUser ){

        m.addAttribute("loginUser" , loginUser);

        MapBoardDto mapBoardDto = mapBoardService.bringMapBoardOne(mapboardno);

        if(loginUser != null){
            if(loginUser.getUserno().equals(mapBoardDto.getMapboard_userno().longValue()))
                m.addAttribute("WriterCheck", "OK");
        }

        m.addAttribute("mapBoardDto" , mapBoardDto);
        m.addAttribute("sc",sc);

        return "/map/findmaplistdetail";
    }

    @GetMapping("/write")
    public String mapBoardWrite(SearchCondition sc, Model m , RedirectAttributes redirectAttributes , @ModelAttribute("msg") String msg, @SessionAttribute(name = "loginUser", required = false) User loginUser ){

        m.addAttribute("loginUser" , loginUser);

        // 로그인 여부 체크
        if(loginUser == null){
            redirectAttributes.addAttribute("page", sc.getPage());
            redirectAttributes.addAttribute("pageSize", sc.getPageSize());
            redirectAttributes.addAttribute("keyword", sc.getKeyword());
            redirectAttributes.addAttribute("ani_category", sc.getAni_category());
            redirectAttributes.addFlashAttribute("msg", "NO_LOGIN");
            return "redirect:/map/list";
        }

        m.addAttribute("sc", sc);
        m.addAttribute("msg",msg);

        return "/map/findmapreg";
    }

    @PostMapping("/write")
    @ResponseBody
    public ResponseEntity<Integer> mapBoardWrite(@RequestParam Boolean mapboard_ani_category , @RequestParam Boolean writertype , @RequestParam String mapboard_title  , @RequestParam String mapboard_content , @RequestParam Date missingtime , @RequestParam String missingAddress , @RequestParam(required = false) Double latitude , @RequestParam(required = false)  Double longitude , @RequestParam(required = false) List<MultipartFile> mapBoardFile , @RequestParam Integer loginUser )
    throws IOException {

        MapBoardDto mapBoardDto = new MapBoardDto("" , loginUser , null , mapboard_title , mapboard_content , missingtime , missingAddress , latitude  , longitude , new Date() , 0 , mapboard_ani_category , writertype ,0 );
        System.out.println(mapBoardDto);
        mapBoardDto.setMapBoardFile(mapBoardFile);

        System.out.println("Post , wirite -mapBoardDto : "+mapBoardDto);

        // 이미지가 있는 경우
        if(mapBoardFile != null){
            mapBoardDto.setFileAttached(1);
        }

        int result = mapBoardService.writeMapBoard(mapBoardDto);
        System.out.println(result);
        return new ResponseEntity<Integer>(result , HttpStatus.OK);
    }
    @GetMapping("/modify")
    public String mapBoardModify(Integer mapboardno , Integer mapboard_userno  , SearchCondition sc , Model m , RedirectAttributes redirectAttributes ,@SessionAttribute(name = "loginUser", required = false) User loginUser ){

        m.addAttribute("loginUser" , loginUser);

        if(loginUser == null){
            // 로그인 안 함
            redirectAttributes.addAttribute("mapboardno" , mapboardno);
            redirectAttributes.addAttribute("page", sc.getPage());
            redirectAttributes.addAttribute("pageSize", sc.getPageSize());
            redirectAttributes.addAttribute("keyword", sc.getKeyword());
            redirectAttributes.addAttribute("ani_category", sc.getAni_category());
            redirectAttributes.addFlashAttribute("msg", "NO_LOGIN");
            return "redirect:/map/read";
        } else if( !loginUser.getUserno().equals(mapboard_userno.longValue())){
            redirectAttributes.addAttribute("mapboardno" , mapboardno);
            redirectAttributes.addAttribute("page", sc.getPage());
            redirectAttributes.addAttribute("page", sc.getPage());
            redirectAttributes.addAttribute("pageSize", sc.getPageSize());
            redirectAttributes.addAttribute("keyword", sc.getKeyword());
            redirectAttributes.addAttribute("ani_category", sc.getAni_category());
            redirectAttributes.addFlashAttribute("msg", "NotEqual");

            return "redirect:/map/read";
        }

        MapBoardDto mapBoardDto = mapBoardService.bringMapBoardOne(mapboardno);

        System.out.println("modify get : "+mapBoardDto);

        m.addAttribute("mapBoardDto" , mapBoardDto);
        m.addAttribute("sc" , sc);
        return "/map/findmapedit";
    }

    @PostMapping("/modify")
    @ResponseBody
    public ResponseEntity<String> mapBoardModify( @RequestParam Integer mapboardno , @RequestParam Integer mapboard_userno , @RequestParam Boolean mapboard_ani_category , @RequestParam Boolean writertype , @RequestParam String mapboard_title , @RequestParam String mapboard_content , @RequestParam Date missingtime , @RequestParam String missingaddress , @RequestParam(required = false)  Double latitude , @RequestParam(required = false)  Double longitude , @RequestParam(required = false) List<MultipartFile> mapBoardFile , @RequestParam Integer fileAttached , @RequestParam Integer loginUser)
    throws IOException{

        MapBoardDto mapBoardDto = new MapBoardDto("" , mapboard_userno  , mapboardno , mapboard_title , mapboard_content , missingtime , missingaddress , latitude  , longitude , new Date() , 0 , mapboard_ani_category , writertype ,fileAttached );
        mapBoardDto.setMapBoardFile(mapBoardFile);
        System.out.println("modify_post mapboardno : "+mapboardno);
        System.out.println("modify_post mapBoardDto : "+mapBoardDto);
        System.out.println("modify_post mapboard_userno : "+mapboard_userno);
        System.out.println("modify_post loginUser : "+loginUser);

        mapBoardService.modifyMapBoard(mapBoardDto , loginUser);

        return new ResponseEntity<String>("성공적으로 수정했습니다.",HttpStatus.OK);
    }


    @PostMapping("/remove")
    public String mapBoardRemove(Integer mapboardno , Integer mapboard_userno ,  Integer loginUserNo ,SearchCondition sc , RedirectAttributes redirectAttributes  , String loginUser) throws IOException{

        System.out.println("remove mapboardno : "+mapboardno);
        System.out.println("remove mapboard_userno : "+mapboard_userno);
        System.out.println("remove loginUserNo : " + loginUserNo);

        //
        if(loginUserNo == 0){
            redirectAttributes.addFlashAttribute("msg" , "NO_LOGIN");
            System.out.println("로그인 안 해서 loginUserNo == "+loginUserNo);
            return "redirect:/map/list";
        } else if( mapboard_userno != loginUserNo){
            // 작성자와 로그인 유저가 일치하지 않는 경우
            redirectAttributes.addAttribute("loginUser" , loginUser);
            redirectAttributes.addAttribute("page" , sc.getPage());
            redirectAttributes.addAttribute("pageSize" , sc.getPageSize());
            redirectAttributes.addAttribute("keyword", sc.getKeyword());
            redirectAttributes.addAttribute("ani_category", sc.getAni_category());
            redirectAttributes.addAttribute("mapboardno",mapboardno);
            redirectAttributes.addFlashAttribute("msg", "NotEqualRemove");
            System.out.println("게시물 작성자 = "+mapboard_userno + " | 로그인 정보 = "+ loginUserNo);
            return "redirect:/map/read";
        }

        // DB 삭제
        int result = mapBoardService.removeMapBoard(mapboardno,loginUserNo);

        // 삭제 실패시 (DB 삭제 여부)
        if(result == 0){
            redirectAttributes.addAttribute("page" , sc.getPage());
            redirectAttributes.addAttribute("pageSize" , sc.getPageSize());
            redirectAttributes.addAttribute("keyword", sc.getKeyword());
            redirectAttributes.addAttribute("ani_category", sc.getAni_category());
            redirectAttributes.addAttribute("mapboardno",mapboardno);
            redirectAttributes.addFlashAttribute("msg", "FAIL_REMOVE");
            return "redirect:/mapboard/read";
        }

        // 삭제 성공
        redirectAttributes.addFlashAttribute("msg" , "SUCCESS_REMOVE");
        return "redirect:/map/list";
    }

    /*@GetMapping("/searchList")
    public String searchList(SearchCondition sc, @SessionAttribute(name = "loginUser", required = false) User loginUser, Model m) {

        if(loginUser != null){
            // 로그인 정보 전달
            m.addAttribute("loginUser" , loginUser);
        }
        System.out.println(sc);
        List<MapBoardDto> mapBoardDtoList = mapBoardService.selectMapList(sc);
        int totalCnt = mapBoardService.getMapBoardListCnt(sc);
        PageHandler ph = new PageHandler(totalCnt , sc);
        m.addAttribute("mapBoardDtoList",mapBoardDtoList);
        m.addAttribute("totalCnt" , totalCnt);
        m.addAttribute("sc", sc);
        m.addAttribute("ph" ,ph);
        return "/map/findmaplist";
    }*/
}
