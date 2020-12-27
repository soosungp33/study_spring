package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    // 정적
    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello!!");
        return "hello";
    }

    // MVC(동적)
    @GetMapping("hello-mvc")
    public String hello(@RequestParam("name") String name, Model model) { // 외부에서 파라미터를 받음
        model.addAttribute("name", name);
        return "hello-template";
    }

    // API(문자)
    @GetMapping("hello-string")
    @ResponseBody // Body부분에 넣을 것것
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name;
    }

    // API(데이터)
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }
    static class Hello{
        private String name;

        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
    }
}