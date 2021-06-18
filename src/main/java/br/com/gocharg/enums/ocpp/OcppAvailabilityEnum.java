package br.com.gocharg.enums.ocpp;

import lombok.Getter;

import java.util.stream.Stream;

@Getter
public enum OcppAvailabilityEnum {
  INOPERATIVE("Inoperative"),
  OPERATIVE("Operative");

  private String status;

  OcppAvailabilityEnum(String status) {
    this.status = status;
  }

  public static OcppAvailabilityEnum get(String status) {
    return Stream.of(OcppAvailabilityEnum.values())
        .filter(value -> value.getStatus().equals(status))
        .findFirst()
        .orElse(null);
  }

  public static boolean contains(String codigo) {
    return Stream.of(OcppAvailabilityEnum.values()).anyMatch(status -> status.equals(codigo));
  }
}
