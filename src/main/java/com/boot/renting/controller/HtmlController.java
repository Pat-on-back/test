package com.boot.renting.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HtmlController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    /*--------------------------landlord------------------------------*/

    @GetMapping("/landlord/{html}")
    public String landlordIndex(@PathVariable("html") String html) {
        return "landlord/" + html;
    }

    /*--------------------------user------------------------------*/

    @GetMapping("/user/{html}")
    public String userIndex(@PathVariable("html") String html) {
        return "user/" + html;
    }

    /*--------------------------admin------------------------------*/

    @GetMapping("/admin/{html}")
    public String adminHtml(@PathVariable("html") String html) {
        return "admin/" + html;
    }
//
//    @GetMapping("/admin/index")
//    public String adminIndex() {
//        return "admin/index";
//    }
//
//    @GetMapping("/admin/login")
//    public String adminLogin() {
//        return "admin/login";
//    }
//
//    @GetMapping("/admin/adminList")
//    public String adminList() {
//        return "admin/adminList";
//    }
//
//    @GetMapping("/admin/userList")
//    public String userList() {
//        return "admin/userList";
//    }
//
//    @GetMapping("/admin/landlordList")
//    public String landlordList() {
//        return "admin/landlordList";
//    }


//
//    @GetMapping("/{html}")
//    public String html(@PathVariable("html") String html) {
//        return html;
//    }
//
//    @GetMapping("/{root}/{html}")
//    public String rootHtm(@PathVariable("root") String root, @PathVariable("html") String html) {
//        return root + "/" + html;
//    }
}
