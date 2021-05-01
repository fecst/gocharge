package br.com.gocharg.enums.ocpp;

import lombok.Getter;

import java.util.stream.Stream;

@Getter
public enum OcppRecurrencyKindEnum {
  DAILY("Daily"),
  WEEKLY("Weekly");

  private String status;

  OcppRecurrencyKindEnum(String status) {
    this.status = status;
  }

  public static OcppRecurrencyKindEnum get(String status) {
    return Stream.of(OcppRecurrencyKindEnum.values())
        .filter(value -> value.equals(status))
        .findFirst()
        .orElse(null);
  }

  public static boolean contains(String codigo) {
    return Stream.of(OcppRecurrencyKindEnum.values()).anyMatch(status -> status.equals(codigo));
  }
}
