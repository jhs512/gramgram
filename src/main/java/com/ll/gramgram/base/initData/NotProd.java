package com.ll.gramgram.base.initData;

import com.ll.gramgram.boundedContext.account.service.AccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile({"dev", "test"})
public class NotProd {
    private boolean initDataDone = false;

    @Bean
    CommandLineRunner initData(
            AccountService accountService
    ) {
        return args -> {
            if (initDataDone) {
                return;
            }

            initDataDone = true;

            accountService.whenSocialLogin("KAKAO", "KAKAO__2712535576", "장희성");
        };
    }
}
