package br.com.gocharg.enums.ocpp;

import lombok.Getter;

import java.util.stream.Stream;

@Getter
public enum OcppUnitEnum {
  WH("Wh"),
  KWH("kWh"),
  VARH("varh"),
  KVARH("kvarh"),
  W("W"),
  KW("kW"),
  VA("VA"),
  KVA("kVA"),
  VAR("var"),
  KVAR("kvar"),
  A("A"),
  V("V"),
  K("K"),
  CELCIUS("Celcius"),
  CELSIUS("Celsius"),
  FAHRENHEIT("Fahrenheit"),
  PERCENT("Percent");

  private String status;

  OcppUnitEnum(String status) {
    this.status = status;
  }

  public static OcppUnitEnum get(String status) {
    return Stream.of(OcppUnitEnum.values())
        .filter(value -> value.equals(status))
        .findFirst()
        .orElse(null);
  }

  public static boolean contains(String codigo) {
    return Stream.of(OcppUnitEnum.values()).anyMatch(status -> status.equals(codigo));
  }
}
