package com.ll.gramgram.boundedContext.member.entity;

import com.ll.gramgram.base.entity.Base;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
@SuperBuilder
@ToString(callSuper = true)
public class Member extends Base {
    private String username;
    private String nickname;
    private String oauthType;

    private String instagramAccountId;
    private String gender;

    public void updateWhenSocialLogin(String nickname) {
        this.nickname = nickname;
    }

    public void updateInstagramAccountIdAndGender(String instagramAccountId, String gender) {
        this.instagramAccountId = instagramAccountId;
        this.gender = gender;
    }
}
