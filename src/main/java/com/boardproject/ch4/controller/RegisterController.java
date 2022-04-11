package com.boardproject.ch4.controller;


import com.boardproject.ch4.domain.UserDto;
import com.boardproject.ch4.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegisterController {
    @Autowired
    UserService userService;

    @GetMapping("/register/add")
    public String RegisterForm(){
        return "registerForm";
    }

    @PostMapping("/login/add")
    public String Register(@ModelAttribute UserDto user, Model model) {
        try {
            if(validDuplicateId(user)){
                userService.insert(user);
            }

        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("msg", e.getMessage());
            return "registerForm";
        }

        model.addAttribute("name", user.getName());
        return "index";
    }

    private boolean validDuplicateId(UserDto user) throws Exception {
        UserDto findUser = userService.selectUserById(user.getId());
        if(findUser != null) throw new IllegalStateException("이미 사용중인 아이디입니다.");

        return true;
    }
}
