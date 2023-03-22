package pers.zymir.lucky;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("pers.zymir.lucky.mapper.*")
public class LuckyApplication {
  public static void main(String[] args) {
    SpringApplication.run(LuckyApplication.class, args);
  }
}
