package br.com.gocharg.enums.ocpp;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

import java.util.stream.Stream;

@Getter
public enum OcppLocationEnum {
  CABLE("Cable"),
  EV("EV"),
  INLET("Inlet"),
  OUTLET("Outlet"),
  BODY("Body");

  private String status;

  OcppLocationEnum(String status) {
    this.status = status;
  }

  @JsonCreator
  public static OcppLocationEnum forValue(String value) {
    return OcppLocationEnum.get(value);
  }

  public static OcppLocationEnum get(String status) {
    return Stream.of(OcppLocationEnum.values())
        .filter(value -> value.equals(status))
        .findFirst()
        .orElse(null);
  }

  public static boolean contains(String codigo) {
    return Stream.of(OcppLocationEnum.values()).anyMatch(status -> status.equals(codigo));
  }
}
