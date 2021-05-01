package br.com.gocharg.enums.ocpp;

import lombok.Getter;

import java.util.stream.Stream;

@Getter
public enum OcppResetEnum {
  HARD("Hard"),
  SOFT("Soft");

  private String status;

  OcppResetEnum(String status) {
    this.status = status;
  }

  public static OcppResetEnum get(String status) {
    return Stream.of(OcppResetEnum.values())
        .filter(value -> value.equals(status))
        .findFirst()
        .orElse(null);
  }

  public static boolean contains(String codigo) {
    return Stream.of(OcppResetEnum.values()).anyMatch(status -> status.equals(codigo));
  }
}
