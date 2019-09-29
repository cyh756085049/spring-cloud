package com.consumer.movieribbonconfig.config;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Configuration;

/**
 * 使用RibbonClient，为特定的目标服务自定义配置。
 * 使用@RibbonClient的configuration属性，指定Ribbon的配置类。
 * 可参考的示例：
 * http://spring.io/guides/gs/client-side-load-balancing/
 */
@Configuration
@RibbonClient(name = "user", configuration = RibbonConfiguration.class)
public class TestConfiguration {
}
