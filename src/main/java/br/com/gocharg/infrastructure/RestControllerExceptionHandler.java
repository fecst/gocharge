package br.com.gocharg.infrastructure;

import br.com.fluentvalidator.context.Error;
import br.com.fluentvalidator.context.ValidationResult;
import br.com.fluentvalidator.transform.ValidationResultTransform;
import br.com.gocharg.domain.defaultResponses.ErrorField;
import br.com.gocharg.domain.defaultResponses.ErrorResponse;
import br.com.gocharg.domain.defaultResponses.FluentResponse;
import br.com.gocharg.exceptions.BadRequestException;
import br.com.gocharg.exceptions.NoContentException;
import br.com.gocharg.exceptions.NotFoundException;
import br.com.gocharg.exceptions.UnprocessableEntityException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Objects;

@RestControllerAdvice
class RestControllerExceptionHandler {

  @ExceptionHandler
  ResponseEntity<Object> handlerException(final NotFoundException e, WebRequest webRequest) {
    return ResponseEntity.status(e.getHttpStatus())
        .body(
            FluentResponse.error()
                .message(e.getMessage())
                .code(String.valueOf(e.getHttpStatus().value())));
  }

  @ExceptionHandler
  ResponseEntity<Object> handlerException(final NoContentException e, WebRequest webRequest) {
    return ResponseEntity.status(e.getHttpStatus())
            .body(
                    FluentResponse.error()
                            .message(e.getMessage())
                            .code(String.valueOf(e.getHttpStatus().value())));
  }

  @ExceptionHandler
  ResponseEntity<Object> handlerException(final UnprocessableEntityException e, WebRequest webRequest) {
    return ResponseEntity.status(e.getHttpStatus())
            .body(
                    FluentResponse.error()
                            .message(e.getMessage())
                            .code(String.valueOf(e.getHttpStatus().value())));
  }

  @ExceptionHandler
  ResponseEntity<Object> handlerException(final IllegalArgumentException e, WebRequest webRequest) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(FluentResponse.error()
                    .message(e.getMessage())
                    .code(String.valueOf(HttpStatus.BAD_REQUEST.value())));
  }

  @ExceptionHandler
  ResponseEntity<Object> handlerException(final BadRequestException e, WebRequest webRequest) {
    final ErrorResponseResultTransform resultTransform =
        new ErrorResponseResultTransform(String.valueOf(e.getHttpStatus().value()), e.getMessage());

    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(resultTransform.transform(e.getValidationResult()));
  }

  class ErrorResponseResultTransform implements ValidationResultTransform<ErrorResponse> {
    private final String code;
    private final String message;

    public ErrorResponseResultTransform(final String code, final String message) {
      this.code = code;
      this.message = message;
    }

    @Override
    public ErrorResponse transform(final ValidationResult result) {
      final ErrorResponse errorResponse = FluentResponse.error().code(code).message(message);

      for (final Error error : result.getErrors()) {
        errorResponse.field(
            new ErrorField(
                error.getField(), error.getMessage(), Objects.toString(error.getAttemptedValue())));
      }

      return errorResponse;
    }
  }
}
