package br.com.gocharg.domain.defaultResponses;

import java.util.List;

public final class FluentResponse<T> {
  public static SuccessResponse success() {
    return new SuccessResponse();
  }

  public static ErrorResponse error() {
    return new ErrorResponse();
  }

  public static ErrorResponse error(
      final String code, final String message, final List<ErrorField> fields) {
    return new ErrorResponse(code, message, fields);
  }
}
