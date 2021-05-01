package br.com.gocharg.enums.ocpp;

import lombok.Getter;

import java.util.stream.Stream;

@Getter
public enum OcppChargingProfileKindEnum {
  ABSOLUTE("Absolute"),
  RECURRING("Recurring"),
  RELATIVE("Relative");

  private String status;

  OcppChargingProfileKindEnum(String status) {
    this.status = status;
  }

  public static OcppChargingProfileKindEnum get(String status) {
    return Stream.of(OcppChargingProfileKindEnum.values())
        .filter(value -> value.equals(status))
        .findFirst()
        .orElse(null);
  }

  public static boolean contains(String codigo) {
    return Stream.of(OcppChargingProfileKindEnum.values())
        .anyMatch(status -> status.equals(codigo));
  }
}
