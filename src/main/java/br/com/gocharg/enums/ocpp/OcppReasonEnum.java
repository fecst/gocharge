package br.com.gocharg.enums.ocpp;

import lombok.Getter;

import java.util.stream.Stream;

@Getter
public enum OcppReasonEnum {
  EMERGENCY_STOP("EmergencyStop"),
  EV_DISCONNECTED("EVDisconnected"),
  LOCAL("Local"),
  OTHER("Other"),
  POWER_LOSS("PowerLoss"),
  REBOOT("Reboot"),
  REMOTE("Remote"),
  SOFT_RESET("SoftReset"),
  UNLOCK_COMMAND("UnlockCommand"),
  DE_AUTHORIZED("DeAuthorized");

  private String status;

  OcppReasonEnum(String status) {
    this.status = status;
  }

  public static OcppReasonEnum get(String status) {
    return Stream.of(OcppReasonEnum.values())
        .filter(value -> value.equals(status))
        .findFirst()
        .orElse(null);
  }

  public static boolean contains(String codigo) {
    return Stream.of(OcppReasonEnum.values()).anyMatch(status -> status.equals(codigo));
  }
}
