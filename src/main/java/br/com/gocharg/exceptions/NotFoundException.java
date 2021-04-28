package br.com.gocharg.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class NotFoundException extends RuntimeException {
  private static final String MESSAGE = "Registro não encontrado.";

  private HttpStatus httpStatus = HttpStatus.NOT_FOUND;
  private String message = MESSAGE;

  public NotFoundException() {}

  public NotFoundException(String message) {
    this.message = message;
  }

}
