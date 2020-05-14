package com.yishi.mall;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.yishi.mall.user.mapper")
@EnableDubbo(scanBasePackages = "com.yishi.mall")
public class UserApplication implements CommandLineRunner {
  public static void main(String[] args) {
    SpringApplication.run(UserApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    Thread.currentThread().join();
  }
}
