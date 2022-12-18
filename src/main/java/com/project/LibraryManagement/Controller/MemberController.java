package com.project.LibraryManagement.Controller;

import com.project.LibraryManagement.Model.Member;
import com.project.LibraryManagement.Service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MemberController {


    @Autowired
    private MemberService memberService;

    @GetMapping(value = {"/librarian/getMembers1"})
    public  @ResponseBody List<Member> getMembersList() {
        return memberService.getAllMembers();
    }


    @PostMapping(value = {"/librarian/postMembers"})
    public ResponseEntity<String> putMember(@RequestBody Member member) {
        return memberService.registerNewMember(member);
    }

    @DeleteMapping(value = "/librarian/deleteMembers/{member_id}")
    public void deleteMember(@PathVariable("member_id") Long id) {
        memberService.deleteMember(id);
    }




    }
