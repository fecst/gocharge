package br.com.gocharg.enums.ocpp;

import lombok.Getter;

import java.util.stream.Stream;

@Getter
public enum OcppUpdateTypeEnum {
  DIFFERENTIAL("Differential"),
  FULL("Full");

  private String status;

  OcppUpdateTypeEnum(String status) {
    this.status = status;
  }

  public static OcppUpdateTypeEnum get(String status) {
    return Stream.of(OcppUpdateTypeEnum.values())
        .filter(value -> value.equals(status))
        .findFirst()
        .orElse(null);
  }

  public static boolean contains(String codigo) {
    return Stream.of(OcppUpdateTypeEnum.values()).anyMatch(status -> status.equals(codigo));
  }
}
