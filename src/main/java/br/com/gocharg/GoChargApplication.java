package br.com.gocharg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackageClasses = GoChargApplication.class)
public class GoChargApplication {

  public static void main(String[] args) {
    SpringApplication.run(GoChargApplication.class, args);
  }
}
