package com.project.poinhanshin.controller.login;


import com.project.poinhanshin.domain.member.User;
import com.project.poinhanshin.mapper.login.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class LoginController {
    private final UserDao userDao;

    @Autowired
    public LoginController(UserDao userDao) {
        this.userDao = userDao;
    }

    @GetMapping("/members/add")
    public String addForm(@ModelAttribute("user") User user) {
        return "login/registerForm";
    }

    @PostMapping("/members/add")
    public String add(User user, BindingResult bindingResult) {
        if (user.getId().trim().length() == 0) {
            bindingResult.reject("id_null", "아이디를 입력하세요");
            return "login/registerForm";
        }
        else if (user.getPassword().trim().length() == 0) {
            bindingResult.reject("pw_null", "비밀번호를 입력하세요");
            return "login/registerForm";
        }
        else if (user.getName().trim().length() == 0) {
            bindingResult.reject("name_null", "이름을 입력하세요");
            return "login/registerForm";
        }
        else if (String.valueOf(user.getAge()).trim().length() == 0) {
            bindingResult.reject("age_null", "나이를 입력하세요");
            return "login/registerForm";
        }
        else if (user.getPhnum().trim().length() == 0) {
            bindingResult.reject("ph_null", "전화번호를 입력하세요");
            return "login/registerForm";
        }
        else if (user.getMail().trim().length() == 0) {
            bindingResult.reject("mail_null", "이메일을 입력하세요");
            return "login/registerForm";
        }
        else if (user.getAddress().trim().length() == 0) {
            bindingResult.reject("add_null", "주소를 입력하세요");
            return "login/registerForm";
        }
        User findUser = userDao.findByLoginId(user.getId());
        if (findUser != null) {
            bindingResult.reject("exist", "이 아이디는 사용중입니다.");
            return "login/registerForm";
        }
        userDao.save(user);
        return "redirect:/";
    }

    @GetMapping("/login")
    public String loginForm(@ModelAttribute("user") User user) {
        return "login/loginForm";
    }

    @PostMapping("/login")
    public String login(User user, BindingResult bindingResult, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        if (user.getId().trim().length() == 0) {
            bindingResult.reject("id_null", "아이디를 입력하세요");
            return "login/loginForm";
        } else if (String.valueOf(user.getPassword()).trim().length() == 0) {
            bindingResult.reject("pw_null", "비밀번호를 입력하세요");
            return "login/loginForm";
        }
        User loginUser = userDao.login(user.getId(), user.getPassword());
        if (loginUser == null) {
            bindingResult.reject("none", "아이디 또는 비밀번호가 일치하지 않습니다.");
            return "login/loginForm";
        }
        HttpSession session = request.getSession();
        session.setAttribute("loginUser", loginUser);
        redirectAttributes.addFlashAttribute("msg", "LOGIN_OK");
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/";
    }

}
