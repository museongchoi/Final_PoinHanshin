package com.project.poinhanshin.controller.mbti;
import com.project.poinhanshin.domain.api.Abandoned_animal;
import com.project.poinhanshin.domain.mbti.MBTINameKind;

import com.project.poinhanshin.domain.member.User;
import com.project.poinhanshin.etc.ApiExplorer;
import com.project.poinhanshin.mapper.mbti.MBTIMapper;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/MBTI")
public class MBTIController {

    ApiExplorer apiExplorer;
    MBTIMapper mbtiMapper;

    @Autowired
    public MBTIController(ApiExplorer apiExplorer, MBTIMapper mbtiMapper) {
        this.apiExplorer = apiExplorer;
        this.mbtiMapper = mbtiMapper;
    }

    /*@Autowired
    public MBTIController(ApiExplorer apiExplorer) {
        this.apiExplorer = apiExplorer;
    }*/

    @GetMapping("/main")
    public String mbti(Model m, @SessionAttribute(name = "loginUser", required = false) User loginUser) throws IOException, ParseException {

        m.addAttribute("loginUser", loginUser);

        Abandoned_animal abandoned_animal[] = apiExplorer.SearchAnimalList("","","","","","","","","","1","6");

        m.addAttribute("AAArr",abandoned_animal);
        return "mbti/mbti_main";
    }

    @GetMapping("/start")
    public String mbtiStart() {
        return "mbti/mbti";
    }

    @GetMapping("/result")
    public String mbtiSearch(String mbti , Model m){
        m.addAttribute("MBTI",mbti);
        return "mbti/mbti_result";
    }

    @GetMapping("/resultPage")
    public String resultPage(Integer point , Model m){
        System.out.println("mbti 테스트 결과 값 "+point);
        List<MBTINameKind> list = mbtiMapper.searchAband(point+1);
        System.out.println(list);

        m.addAttribute("AnimalDatas" , list);
        return "/mbti/page/result-"+point;
    }


}
