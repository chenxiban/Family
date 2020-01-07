package com.cyj.resourcecenter.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import javax.servlet.MultipartConfigElement;

/**
 * 上传配置类
 * 图片放到/F:/fileUpload/后，从磁盘读取的图片数据scr将会变成images/picturename.jpg的格式
 */
@Slf4j
@Configuration
public class WebAppConfig extends WebMvcConfigurationSupport {
    //public class WebAppConfig extends WebMvcConfigurerAdapter {
    /**
     * 在配置文件中配置的文件保存路径
     */
    @Value("${cbs.imagesPath}")
    private String mImagesPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String str = "";
        String str2 = "${cbs.imagesPath}";
        if (mImagesPath.equals(str) || mImagesPath.equals(str2)) {
            String imagesPath = WebAppConfig.class.getClassLoader().getResource("").getPath();
            log.info("1.上传配置类imagesPath==" + imagesPath + "\n");
            if (imagesPath.indexOf(".jar") > 0) {
                imagesPath = imagesPath.substring(0, imagesPath.indexOf(".jar"));
            } else if (imagesPath.indexOf("classes") > 0) {
                imagesPath = "file:" + imagesPath.substring(0, imagesPath.indexOf("classes"));
            }
            imagesPath = imagesPath.substring(0, imagesPath.lastIndexOf("/")) + "/images/";
            mImagesPath = imagesPath;
        }
        log.info("imagesPath=============>" + mImagesPath + "\n");
        registry.addResourceHandler("/images/**").addResourceLocations(mImagesPath);
        log.info("2.上传配置类mImagesPath==>" + mImagesPath + "\n");
        super.addResourceHandlers(registry);
    }

}
