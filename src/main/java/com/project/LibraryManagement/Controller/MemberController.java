package com.project.LibraryManagement.Controller;

import com.project.LibraryManagement.Model.Member;
import com.project.LibraryManagement.Service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin
public class MemberController {


    @Autowired
    private MemberService memberService;

    @GetMapping(value = {"/librarian/getMembers1"})
    public  @ResponseBody List<Member> getMembersList() {
        return memberService.getAllMembers();
    }


    @PostMapping(value = {"/librarian/postMember"})
    public ResponseEntity<Object> postMember(@RequestBody Member member) {
        return memberService.registerNewMember(member);
    }
    @PutMapping(value = {"/librarian/updateMember"})
    public ResponseEntity<Object> putMember(@RequestBody Member member) {
        return memberService.updateMember(member);
    }

    @DeleteMapping(value = "/librarian/deleteMembers/{member_id}")
    public ResponseEntity<Object> deleteMember(@PathVariable("member_id") Long id) {
        return memberService.deleteMember(id);
    }




    }
