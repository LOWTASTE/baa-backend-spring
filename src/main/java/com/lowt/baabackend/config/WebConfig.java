package com.lowt.baabackend.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${img.upload-path}")
    private String webUploadPath;

    @Value("${headImg.upload-path}")
    private String headImgUploadPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/baabackend/img/**")
                .addResourceLocations("file:" + webUploadPath);
        registry.addResourceHandler("/baabackend/headimg/**")
                .addResourceLocations("file:" + headImgUploadPath);

    }
}
