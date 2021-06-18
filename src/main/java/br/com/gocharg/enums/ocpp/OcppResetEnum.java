package br.com.gocharg.enums.ocpp;

import lombok.Getter;

import java.util.stream.Stream;

@Getter
public enum OcppResetEnum {
  HARD("Hard"),
  SOFT("Soft");

  private String type;

  OcppResetEnum(String type) {
    this.type = type;
  }

  public static OcppResetEnum get(String status) {
    return Stream.of(OcppResetEnum.values())
        .filter(value -> value.getType().equals(status))
        .findFirst()
        .orElse(null);
  }

  public static boolean contains(String codigo) {
    return Stream.of(OcppResetEnum.values()).anyMatch(status -> status.equals(codigo));
  }
}
