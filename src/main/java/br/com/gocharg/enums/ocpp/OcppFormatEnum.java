package br.com.gocharg.enums.ocpp;

import lombok.Getter;

import java.util.stream.Stream;

@Getter
public enum OcppFormatEnum {
  RAW("Raw"),
  SIGNED_DATA("SignedData");

  private String status;

  OcppFormatEnum(String status) {
    this.status = status;
  }

  public static OcppFormatEnum get(String status) {
    return Stream.of(OcppFormatEnum.values())
        .filter(value -> value.equals(status))
        .findFirst()
        .orElse(null);
  }

  public static boolean contains(String codigo) {
    return Stream.of(OcppFormatEnum.values()).anyMatch(status -> status.equals(codigo));
  }
}
