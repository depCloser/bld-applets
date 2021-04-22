package com.bld.applets;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@MapperScan("com.bld.applets.mapper")
@ServletComponentScan("com.bld.applets.filter")
public class AppletsApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppletsApplication.class, args);
    }

}
