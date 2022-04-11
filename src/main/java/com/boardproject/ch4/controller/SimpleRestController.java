package com.boardproject.ch4.controller;

import com.boardproject.ch4.domain.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

@Controller
//@RestController
public class SimpleRestController {

    @GetMapping("/ajax")
    public String ajax() {
        return "ajax";
    }

    @PostMapping("/send")
    @ResponseBody
    public Person test(@RequestBody Person p) {  // jackson-databind 라이브러리를 pom.xml에 추가. 이 라이브러리는 문자열을 객체로 만들어준다.
//        따라서 이 메서드의 Person p 로 값을 받을 수 있음.
        System.out.println("p = " + p);
        p.setName("ABC");
        p.setAge(p.getAge() + 10);

        return p;   // 객체를 반환한다. jackson-databind 라이브러리가 객체를 JSON 문자열로 변경해주면,
        // ajax.jsp에서 parse메서드를 통해 JS 객체로 변환된다.
    }
}