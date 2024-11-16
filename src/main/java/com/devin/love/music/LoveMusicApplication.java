package com.devin.love.music;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@MapperScan("com.devin.love.music.mapper")
public class LoveMusicApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoveMusicApplication.class, args);
    }

}
