package com.bld.applets;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.bld.applets.mapper")
public class AppletsApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppletsApplication.class, args);
    }

}
