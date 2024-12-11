package com.devin.love.music.common;

import cn.hutool.extra.template.engine.freemarker.FreemarkerTemplate;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * 2024/11/28 16:19
 * <p>
 *     mybatis-plus代码生成器
 * </p>
 * @author <a href="https://github.com/wzh-devin">devin</a>
 * @version 1.0
 * @since 1.0
 */
@SpringBootTest
public class CodeGenerator {

    private static final DataSourceConfig.Builder DATA_SOURCE_CONFIG = new DataSourceConfig.Builder(
            "jdbc:mysql://localhost:3306/love_music?useUnicode=true&useSSL=false&characterEncoding=utf8&serverTimezone=UTC",
            "root",
            "wzh7230610"
    );

    @Test
    public void genderCode() {
        FastAutoGenerator.create(DATA_SOURCE_CONFIG)
                .globalConfig(builder -> {
                    builder.author("<a href=\"https://github.com/wzh-devin\">devin</a>")
                            .outputDir("src\\main\\java");
                })
                .packageConfig(builder -> {
                    builder.parent("com.devin.love.music")
                            .entity("domain.entity")
                            .service("service.v1")
                            .serviceImpl("service.v1.impl")
                            .mapper("mapper.v1")
                            .xml("mapper.v1");
                })
                .strategyConfig(builder -> {
                    builder
                            .addTablePrefix("tb_")
                            .addInclude("tb_file")
                            .entityBuilder() // 生成实体
                            .enableLombok()
                            .enableTableFieldAnnotation()
                            .build();
                })
                .execute();
    }
}
