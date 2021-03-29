package br.com.gocharge.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class UnprocessableEntityException extends RuntimeException {

  private HttpStatus httpStatus = HttpStatus.UNPROCESSABLE_ENTITY;
  private String message;

  public UnprocessableEntityException(String message) {
    this.message = message;
  }

}
