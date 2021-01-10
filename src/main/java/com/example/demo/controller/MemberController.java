package com.example.demo.controller;

import com.example.demo.domain.Member;
import com.example.demo.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {
    private final MemberService memberService;

    @Autowired // 연결(멤버 컨트롤러가 생성될 때 스프링빈에 등록되어있는 멤버 서비스 객체를 가져와서 넣어준다. -> 의존관계 주입)
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new") // 회원 등록을 눌렀을 때(Get은 url 창에 치는거와 똑같음, 조회할 때 사용)
    public String createForm() {
        return "members/createMemberForm"; // 이 위치로 이동(templates에서 찾는다.)
    }

    @PostMapping("/members/new") // 회원 등록 페이지에서 등록을 눌렀을 때(Post는 데이터를 Form같은 곳에 넣어서 전달할 때 사용, 등록할 때 사용)
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers(); // 모델에 있는 데이터를 전부 불러옴
        model.addAttribute("members", members);

        return "members/memberList";
    }
}
