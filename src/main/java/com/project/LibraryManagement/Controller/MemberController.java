package com.project.LibraryManagement.Controller;

import com.project.LibraryManagement.Model.Member;

import com.project.LibraryManagement.Model.Thesis;
import com.project.LibraryManagement.Model.Writer;
import com.project.LibraryManagement.Service.MemberService;
import com.project.LibraryManagement.Service.WriterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
public class MemberController {


    @Autowired
    private MemberService memberService;

    @GetMapping(value = {"/getMembers"})
    public List<Member> getMembersList() {
        return memberService.getAllMembers();
    }


    @PostMapping(value = {"/postMembers"})
    public ResponseEntity<String> putMember(@RequestBody Member member) {
        return memberService.registerNewMember(member);
    }

    @DeleteMapping(value = "/deleteMembers/{member_id}")
    public void deleteMember(@PathVariable("member_id") Long id) {
        memberService.deleteMember(id);
    }




    }
