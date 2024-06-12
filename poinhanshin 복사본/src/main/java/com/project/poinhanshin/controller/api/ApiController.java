package com.project.poinhanshin.controller.api;

import com.project.poinhanshin.domain.api.Abandoned_animal;
import com.project.poinhanshin.domain.api.Shelter;
import com.project.poinhanshin.domain.etc.PageHandler;
import com.project.poinhanshin.domain.etc.SearchCondition;
import com.project.poinhanshin.domain.member.User;
import com.project.poinhanshin.etc.ApiExplorer;
import com.project.poinhanshin.service.api.ApiService;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.io.IOException;



@Controller
@RequestMapping("/api")
public class ApiController {
    ApiExplorer apiExplorer;

    ApiService apiService;

    @Autowired
    public ApiController(ApiExplorer apiExplorer, ApiService apiService) {
        this.apiExplorer = apiExplorer;
        this.apiService = apiService;
    }

    @GetMapping("/AnimalList")
    public String AnimalList(SearchCondition sc , Model m, @SessionAttribute(name = "loginUser", required = false) User loginUser) throws IOException, ParseException {
        System.out.println("실행 됨" + sc + " " + sc.getKind());
        m.addAttribute("loginUser", loginUser);

        if(sc.getPage() == null) sc.setPage(1);
        if(sc.getKind() == null) sc.setKind("");
        // 품종 검색을 위한 삼항연산자
        Abandoned_animal abandoned_animal[] = apiExplorer.SearchAnimalList("","","",sc.getKind(),"","","","","",sc.getPage().toString(),"8");

        int totalCnt = Integer.parseInt(abandoned_animal[0].getTotalCount());
        PageHandler ph = new PageHandler(totalCnt , sc);
        m.addAttribute("kind" , sc.getKind());
        m.addAttribute("totalCnt",totalCnt);
        m.addAttribute("ph",ph);
        m.addAttribute("AAArr",abandoned_animal);

        return "api/AnimalList";
    }

    /*@GetMapping("/AnimalListForKind")
    public String AnimalListForKind(SearchCondition sc , String kind , Model m, @SessionAttribute(name = "loginUser", required = false) User loginUser) throws IOException, ParseException {
        System.out.println("실행 됨" + sc + " " + kind);
        m.addAttribute("loginUser", loginUser);

        if(sc.getPage() == null) sc.setPage(1);
        if(kind == null) kind ="";
        // 품종 검색을 위한 삼항연산자
        Abandoned_animal abandoned_animal[] = apiExplorer.SearchAnimalList("","","",kind,"","","","","",sc.getPage().toString(),"8");

        int totalCnt = Integer.parseInt(abandoned_animal[0].getTotalCount());
        PageHandler ph = new PageHandler(totalCnt , sc);
        m.addAttribute("totalCnt",totalCnt);
        m.addAttribute("ph",ph);
        m.addAttribute("AAArr",abandoned_animal);

        return "api/AnimalList";
    }*/

    @GetMapping("/ShelterList")
    public String ShelterList(SearchCondition sc, Model m, @SessionAttribute(name = "loginUser", required = false) User loginUser) throws IOException, ParseException {

        m.addAttribute("loginUser", loginUser);

        System.out.println(sc.toString());
        if(sc.getKeyword() == null) sc.setKeyword("");
        if(sc.getPage() == null) sc.setPage(1);

        Shelter shelterList[] = apiExplorer.SearchShelterList("",sc.getKeyword(),"10", sc.getPage().toString());
        int totalCnt = Integer.parseInt(shelterList[0].getTotalCount());
        PageHandler ph = new PageHandler(totalCnt , sc);
        m.addAttribute("totalCnt",totalCnt);
        m.addAttribute("ph",ph);
        m.addAttribute("ShelterList", shelterList);
        return "api/ShelterList";
    }

    @GetMapping("/AnimalBoard")
    public String AnimalBoard(Abandoned_animal aban_ani , Model m, @SessionAttribute(name = "loginUser", required = false) User loginUser){

        m.addAttribute("loginUser", loginUser);

        System.out.println(aban_ani);
        m.addAttribute("aban_ani",aban_ani);
        return "api/AnimalBoard";
    }

    @GetMapping("/ShelterBoard")
    public String ShelterBoard(Shelter shelter , Model m, @SessionAttribute(name = "loginUser", required = false) User loginUser){
        m.addAttribute("loginUser", loginUser);
        m.addAttribute("shelter",  shelter);
        return "api/ShelterBoard";
    }

    @GetMapping("test")
    public String test() throws IOException, ParseException {
        apiService.renewal(apiExplorer.getKind());
        return "test/likeDBTest";
    }
    /*@GetMapping("/MBTI")
    public String MBTIMain(Model m) throws IOException, ParseException {
        m.addAttribute("AAArr",apiExplorer.SearchAnimalList("","","","","","","","","","1","4"));
        return "mbti/MBTIMain";
    }
    @GetMapping("/MBTITest")
    public String MBTITest(Model m){
        return "mbti/MBTITest";
    }
    @GetMapping("/MBTIResult")
    public String MBTIResult(Model m){
        return "mbti/MBTIResult";
    }
*/
}
