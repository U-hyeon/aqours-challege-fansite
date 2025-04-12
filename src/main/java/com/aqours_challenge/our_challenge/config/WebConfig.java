package com.aqours_challenge.our_challenge.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Paths;

@Configuration
@Slf4j
public class WebConfig implements WebMvcConfigurer {
    @Value("${gallery.root.path}")
    private String galleryRootPath;

    /**
     * 루트경로 밖에 저장된 이미지 파일로 접근
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/**")
                .addResourceLocations("file:" + Paths.get(galleryRootPath).toAbsolutePath());
    }
}
