package com.github.joponie.flyer.portal.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.github.joponie.flyer.portal.dal.plugins.CustomInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author kain
 * @since 2019-11-04
 */
@Configuration
public class MybatisPlusConfig {

    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

    @Bean
    public CustomInterceptor customInterceptor() {
        return new CustomInterceptor();
    }
}
