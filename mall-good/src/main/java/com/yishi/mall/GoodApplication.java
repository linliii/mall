package com.yishi.mall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class GoodApplication {

    public static void main(String[] args) {
        SpringApplication.run(GoodApplication.class, args);
    }

}
