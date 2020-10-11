package cn.tedu.straw.gateway.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SystemController {
    @GetMapping("/index.html")
    public String index(){
        return "index";
    }
    @GetMapping("/question/create.html")
    public String createQuestion(){
        return "question/create";
    }
    //http:localhost/question/detail.html
    @GetMapping("/question/detail.html")
    public String questionDetail(){
        return "question/detail";
    }
}
