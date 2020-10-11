package cn.tedu.straw.api.user.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {
    @GetMapping("/admin/delete")
    public String delete(){
        return "admin delete";
    }
    @GetMapping("/admin/update")
    public String update(){
        return "admin update";
    }
    @GetMapping("/user/list")
    public String list(){
        return "user list";
    }
    @GetMapping("/user/info")
    public String info(){
        return "user info";
    }
}
