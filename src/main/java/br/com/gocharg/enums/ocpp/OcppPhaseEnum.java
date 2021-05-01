package br.com.gocharg.enums.ocpp;

import lombok.Getter;

import java.util.stream.Stream;

@Getter
public enum OcppPhaseEnum {
  L1("L1"),
  L2("L2"),
  L3("L3"),
  N("N"),
  L1_N("L1-N"),
  L2_N("L2-N"),
  L3_N("L3-N"),
  L1_L2("L1-L2"),
  L2_L3("L2-L3"),
  L3_L1("L3-L1");

  private String status;

  OcppPhaseEnum(String status) {
    this.status = status;
  }

  public static OcppPhaseEnum get(String status) {
    return Stream.of(OcppPhaseEnum.values())
        .filter(value -> value.equals(status))
        .findFirst()
        .orElse(null);
  }

  public static boolean contains(String codigo) {
    return Stream.of(OcppPhaseEnum.values()).anyMatch(status -> status.equals(codigo));
  }
}
