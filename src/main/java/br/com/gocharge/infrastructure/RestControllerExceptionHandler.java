package br.com.gocharge.infrastructure;

import br.com.gocharge.domain.defaultResponses.FluentResponse;
import br.com.gocharge.exceptions.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
class RestControllerExceptionHandler {

  @ExceptionHandler
  ResponseEntity<Object> handlerException(
      final NotFoundException exception, WebRequest webRequest) {
    return ResponseEntity.status(exception.getHttpStatus())
        .body(
            FluentResponse.error()
                .message(exception.getMessage())
                .code(String.valueOf(exception.getHttpStatus().value())));
  }
}
