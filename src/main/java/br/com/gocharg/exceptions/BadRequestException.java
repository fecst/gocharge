package br.com.gocharg.exceptions;

import br.com.fluentvalidator.context.ValidationResult;
import br.com.fluentvalidator.exception.ValidationException;
import lombok.Getter;
import org.springframework.http.HttpStatus;

public class BadRequestException extends ValidationException {
  private static final String MESSAGE = "Erro na validação dos campos";

  @Getter private final HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
  @Getter private final String message = MESSAGE;

  public BadRequestException(final ValidationResult result) {
    super(result);
  }
}
