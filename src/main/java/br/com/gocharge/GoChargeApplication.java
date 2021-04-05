package br.com.gocharge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackageClasses = GoChargeApplication.class)
public class GoChargeApplication {

  public static void main(String[] args) {
    SpringApplication.run(GoChargeApplication.class, args);
  }
}
