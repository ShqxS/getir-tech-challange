package com.kaya.bookservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class BookServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(BookServiceApplication.class, args);
  }
}
