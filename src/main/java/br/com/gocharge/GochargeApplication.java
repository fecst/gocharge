package br.com.gocharge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackageClasses = GochargeApplication.class)
public class GochargeApplication {

  public static void main(String[] args) {
    SpringApplication.run(GochargeApplication.class, args);
  }
}
