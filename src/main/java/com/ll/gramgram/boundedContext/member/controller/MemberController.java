package com.ll.gramgram.boundedContext.member.controller;

import com.ll.gramgram.base.rq.Rq;
import com.ll.gramgram.base.rsData.RsData;
import com.ll.gramgram.boundedContext.member.service.MemberService;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
    private final Rq rq;
    private final MemberService memberService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/connectInstagramAccount")
    public String showConnectInstagramAccount() {
        return "usr/member/connectInstagramAccount";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/connectInstagramAccount")
    public String connectInstagramAccount(ConnectInstagramAccountReqBody reqBody) {
        RsData rs = memberService.saveInstagramAccount(rq.getMember(), reqBody.getInstagramAccountId(), reqBody.gender);

        return Rq.redirectWithMsg("/likeablePerson/manage", rs);
    }

    @Getter
    @AllArgsConstructor
    public static class ConnectInstagramAccountReqBody {
        @NotBlank
        private final String instagramAccountId;
        @NotBlank
        private final String gender;
    }
}
