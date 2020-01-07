package com.cyj.resourcecenter.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author
 * @Description: 默认控制器
 * @ClassName:
 * @Date
 * @Email
 */
@Controller
public class DefaultController {
    /**
     * 跳转上传页面
     * http://localhost:4010/FileSliceUpload.html
     *
     * @return
     */
    @GetMapping("/axiosFileSliceUpload")
    public String forWarding() {
        return "axiosFileSliceUpload";
    }

}
