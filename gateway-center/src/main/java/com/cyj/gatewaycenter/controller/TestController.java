package com.cyj.gatewaycenter.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 测试路由--控制器
 * @BelongsProject: Family
 * @BelongsPackage: com.cyj.gatewaycenter.controller
 * @Author: ChenYongJia
 * @CreateTime: 2020-01-02 15:52
 * @Email: chen87647213@163.com
 * @Version: 1.0
 */
@RestController
public class TestController {

    @Value("${server.port}")
    private String port;

    /**
     * 测试调用请求
     *
     * @param time
     * @return
     */
    @RequestMapping(value = "/get/{time}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> get(@PathVariable("time") long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(port + " get ok.", HttpStatus.OK);
    }

    /**
     * 发生熔断调用的请求
     *
     * @return
     */
    @RequestMapping(value = "/fallback", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> fallback() {
        return new ResponseEntity<>("error.", HttpStatus.OK);
    }

    /**
     * 发生熔断调用的请求
     *
     * @return
     */
    @RequestMapping(value = "/fallbacks", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping("/fallback")
    public Object fallbacks() {
        Map<String, Object> result = new HashMap<>();
        result.put("data", null);
        result.put("message", "Get request fallback!");
        result.put("code", 500);
        return result;
    }

}
