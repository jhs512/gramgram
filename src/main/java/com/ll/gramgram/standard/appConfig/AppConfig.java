package com.ll.gramgram.standard.appConfig;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Getter
    private static ApplicationContext context;
    private static String activeProfile;
    @Getter
    private static String siteName;
    @Getter
    private static String siteBaseUrl;
    @Getter
    private static String genDirName;
    @Getter
    private static int likeablePersonManageInputCount;

    private static String genFileDirParentPath;

    public static String getGenFileDirPath() {
        return genFileDirParentPath + "/" + genDirName;
    }

    @Autowired
    public void setContext(ApplicationContext context) {
        AppConfig.context = context;
    }

    @Value("${spring.profiles.active}")
    public void setActiveProfile(String value) {
        activeProfile = value;
    }

    @Value("${custom.site.name}")
    public void setSiteName(String siteName) {
        AppConfig.siteName = siteName;
    }

    @Value("${custom.site.baseUrl}")
    public void setSiteBaseUrl(String siteBaseUrl) {
        AppConfig.siteBaseUrl = siteBaseUrl;
    }

    @Value("${custom.genFile.dirName}")
    public void setGenDirName(String genDirName) {
        AppConfig.genDirName = genDirName;
    }

    @Value("${custom.genFile.dirParentPath}")
    public void setGenFileDirParentPath(String genFileDirParentPath) {
        AppConfig.genFileDirParentPath = genFileDirParentPath;
    }

    @Value("${custom.likeablePerson.manage.inputCount}")
    public void setLikeablePersonManageInputCount(int likeablePersonManageInputCount) {
        AppConfig.likeablePersonManageInputCount = likeablePersonManageInputCount;
    }

    public static boolean isNotProd() {
        return isProd() == false;
    }

    public static boolean isProd() {
        return activeProfile.equals("prod");
    }

    public static boolean isNotDev() {
        return isLocal() == false;
    }

    public static boolean isLocal() {
        return activeProfile.equals("local");
    }

    public static boolean isNotTest() {
        return isLocal() == false;
    }

    public static boolean isTest() {
        return activeProfile.equals("test");
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper().registerModule(new JavaTimeModule());
    }
}
