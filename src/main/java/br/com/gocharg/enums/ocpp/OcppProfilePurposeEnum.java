package br.com.gocharg.enums.ocpp;

import lombok.Getter;

import java.util.stream.Stream;

@Getter
public enum OcppProfilePurposeEnum {
  CHARGE_POINT_MAX_PROFILE("ChargePointMaxProfile"),
  TX_DEFAULT_PROFILE("TxDefaultProfile"),
  TX_PROFILE("TxProfile");

  private String status;

  OcppProfilePurposeEnum(String status) {
    this.status = status;
  }

  public static OcppProfilePurposeEnum get(String status) {
    return Stream.of(OcppProfilePurposeEnum.values())
        .filter(value -> value.equals(status))
        .findFirst()
        .orElse(null);
  }

  public static boolean contains(String codigo) {
    return Stream.of(OcppProfilePurposeEnum.values()).anyMatch(status -> status.equals(codigo));
  }
}
