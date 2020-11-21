package com.lengedyun.gateway.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @title: TestController
 * @description: TODO
 * @auther: 张健云
 * @date: 2020/11/18 7:06
 */

@RestController
@RequestMapping("gateway-test")
@RefreshScope //nacos管理的配置属性动态刷新
public class TestController {

    @Value("${your.config}")
    private String nacosConfig;

    @GetMapping("getNacosConfig")
    public String toString() {
        return this.nacosConfig;
    }
}
