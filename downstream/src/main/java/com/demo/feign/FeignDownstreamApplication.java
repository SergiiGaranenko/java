package com.demo.feign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FeignDownstreamApplication {

  public static void main(String[] args) {
    SpringApplication.run(FeignDownstreamApplication.class, args);
  }

}
