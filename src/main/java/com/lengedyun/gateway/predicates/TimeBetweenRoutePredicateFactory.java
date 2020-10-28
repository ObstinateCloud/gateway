package com.lengedyun.gateway.predicates;

import com.lengedyun.gateway.config.TimeBetweenConfig;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * @title: TimeBetweenRoutePredicateFactory
 * @description: 谓词工厂约定以RoutePredicateFactory结尾
 * 1.泛型 为配置类
 * 2.实现时间区间的路由谓词工厂
 * @auther: 张健云
 * @date: 2020/10/22 7:52
 */

@Component
public class TimeBetweenRoutePredicateFactory extends AbstractRoutePredicateFactory<TimeBetweenConfig> {


    public TimeBetweenRoutePredicateFactory() {
        super(TimeBetweenConfig.class);
    }

    /**
     * 规则判定
     * @param config
     * @return
     */
    @Override
    public Predicate<ServerWebExchange> apply(TimeBetweenConfig config) {
        LocalTime start = config.getStart();
        LocalTime end = config.getEnd();
        return exchange ->{
            LocalTime now = LocalTime.now();
            return now.isAfter(start) && now.isBefore(end);
        };
    }

    /**
     * 定义配置文件中参数顺序与config类中属性名称一致
     * @return
     */
    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList("start","end");
    }

    public static void main(String[] args) {
        //时间格式根据spring-cloud-gateway 默认时间格式做处理
        //来源类 org.springframework.format.datetime.standard.DateTimeFormatterRegistrar
        //时间格式 DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)  形如 8:15am 或 8:00:00
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT);
        System.out.println(dateTimeFormatter.format(LocalTime.now()));
    }
}
