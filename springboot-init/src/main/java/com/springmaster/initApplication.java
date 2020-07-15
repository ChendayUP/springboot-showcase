package com.springmaster;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Repository;

@SpringBootApplication
@MapperScan(basePackages = "com.springmaster.dao", annotationClass = Repository.class)
class initApplication {
  public static void main(String[] args) {
    SpringApplication.run(initApplication.class, args);
  }
}

