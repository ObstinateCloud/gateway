package com.lengedyun.gateway.config;

import lombok.Data;

import java.time.LocalTime;

/**
 * @title: TimeBetweenConfig
 * @description: TODO
 * @auther: 张健云
 * @date: 2020/10/22 7:54
 */
@Data
public class TimeBetweenConfig {

    private LocalTime start;

    private LocalTime end;
}
