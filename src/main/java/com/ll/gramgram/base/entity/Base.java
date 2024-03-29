package com.ll.gramgram.base.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Getter
@SuperBuilder
@MappedSuperclass
@NoArgsConstructor(access = PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@ToString
public class Base {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    @CreatedDate
    private LocalDateTime createDate;
    @LastModifiedDate
    private LocalDateTime modifyDate;
    @Transient // 아래 필드가 DB 필드가 되는 것을 막는다.
    @Builder.Default
    // 이 필드 덕분에 다양한 DTO 클래스를 만들 필요성이 줄어들게 된다.
    // 하지만 이 방식은 DTO 방식에 비해서 휴먼에러가 일어날 확률을 높힌다.
    // 그것은 TDD로 보완 해야 한다.
    private Map<String, Object> extra = new LinkedHashMap<>();

    public Base(long id) {
        this.id = id;
    }

    public String getModelName() {
        return StringUtils.uncapitalize(this.getClass().getSimpleName());
    }
}
