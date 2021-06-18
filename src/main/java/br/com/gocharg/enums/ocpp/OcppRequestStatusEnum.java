package br.com.gocharg.enums.ocpp;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

import java.util.stream.Stream;

@Getter
public enum OcppRequestStatusEnum {
  AVAILABLE("Available"),
  PREPARING("Preparing"),
  CHARGING("Charging"),
  SUSPENDED_EV_SE("SuspendedEVSE"),
  SUSPENDED_EV("SuspendedEV"),
  FINISHING("Finishing"),
  RESERVED("Reserved"),
  UNAVAILABLE("Unavailable"),
  FAULTED("Faulted");

  private String status;

  OcppRequestStatusEnum(String status) {
    this.status = status;
  }

  public static OcppRequestStatusEnum get(String status) {
    return Stream.of(OcppRequestStatusEnum.values())
        .filter(value -> value.getStatus().equals(status))
        .findFirst()
        .orElse(null);
  }

  @JsonCreator
  public static OcppRequestStatusEnum forValue(String value) {
    return OcppRequestStatusEnum.get(value);
  }

  public static boolean contains(String codigo) {
    return Stream.of(OcppRequestStatusEnum.values()).anyMatch(status -> status.equals(codigo));
  }
}
