package com.ll.gramgram.boundedContext.account.service;

import com.ll.gramgram.base.User;
import com.ll.gramgram.boundedContext.member.entity.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class OAuth2UserService extends DefaultOAuth2UserService {
    private final AccountService accountService;

    // 카카오톡 로그인이 성공할 때 마다 이 함수가 실행된다.
    @Override
    @Transactional
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        Map<String, Object> attributes = oAuth2User.getAttributes();

        String oauthId = oAuth2User.getName();

        String oauthType = userRequest.getClientRegistration().getRegistrationId().toUpperCase();

        if (!"KAKAO".equals(oauthType)) {
            throw new RuntimeException("카카오로그인만 가능합니다.");
        }

        String nickname = null;
        String username = null;

        switch (oauthType) {
            case "KAKAO" -> {
                Map attributesProperties = (Map) attributes.get("properties");
                nickname = (String) attributesProperties.get("nickname");
                username = "KAKAO__%s".formatted(oauthId);
            }
        }

        // TODO: 회원가입, 로그인 처리
        Member member = accountService.whenSocialLogin(oauthType, username, nickname);

        return User.from(member);
    }
}
