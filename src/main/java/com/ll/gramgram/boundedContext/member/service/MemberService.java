package com.ll.gramgram.boundedContext.member.service;

import com.ll.gramgram.base.rsData.RsData;
import com.ll.gramgram.boundedContext.member.entity.Member;
import com.ll.gramgram.boundedContext.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
    private final MemberRepository memberRepository;

    public Optional<Member> getMemberByUsername(String username) {
        return memberRepository.findMemberByUsername(username);
    }

    @Transactional
    public Member join(String oauthType, String username, String nickname) {
        Member member = Member.builder()
                .oauthType(oauthType)
                .username(username)
                .nickname(nickname)
                .build();

        memberRepository.save(member);

        return member;
    }

    @Transactional
    public RsData saveInstagramAccount(Member member, String instagramAccountId, String gender) {
        member.updateInstagramAccountIdAndGender(instagramAccountId, gender);

        return RsData.of("S-1", "인스타그램 계정정보를 설정하였습니다.");
    }
}
