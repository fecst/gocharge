package br.com.gocharg.enums.ocpp;

import lombok.Getter;

import java.util.stream.Stream;

@Getter
public enum OcppChargingRateUnitEnum {
  A("A"),
  W("W");

  private String status;

  OcppChargingRateUnitEnum(String status) {
    this.status = status;
  }

  public static OcppChargingRateUnitEnum get(String status) {
    return Stream.of(OcppChargingRateUnitEnum.values())
        .filter(value -> value.equals(status))
        .findFirst()
        .orElse(null);
  }

  public static boolean contains(String codigo) {
    return Stream.of(OcppChargingRateUnitEnum.values()).anyMatch(status -> status.equals(codigo));
  }
}
