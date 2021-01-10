package com.example.demo.controller;

import com.example.demo.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {
    private final MemberService memberService;

    @Autowired // 연결(멤버 컨트롤러가 생성될 때 스프링빈에 등록되어있는 멤버 서비스 객체를 가져와서 넣어준다. -> 의존관계 주입)
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
