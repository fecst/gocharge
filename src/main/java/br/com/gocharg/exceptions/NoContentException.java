package br.com.gocharg.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public class NoContentException extends RuntimeException {
  private static final String MESSAGE = "Não existem registros.";

  @Getter private final HttpStatus httpStatus = HttpStatus.NO_CONTENT;
  @Getter private final String message = MESSAGE;

  public NoContentException() {}
}
