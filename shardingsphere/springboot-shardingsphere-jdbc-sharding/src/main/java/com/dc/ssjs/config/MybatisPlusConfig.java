package com.dc.ssjs.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * MybatisPlus配置
 *
 * @author duchao
 */
@Configuration
@MapperScan("com.dc.ssjs.mapper")
public class MybatisPlusConfig {
}
