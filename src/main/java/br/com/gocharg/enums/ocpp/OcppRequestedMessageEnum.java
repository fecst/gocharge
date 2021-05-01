package br.com.gocharg.enums.ocpp;

import lombok.Getter;

import java.util.stream.Stream;

@Getter
public enum OcppRequestedMessageEnum {
  BOOT_NOTIFICATION("BootNotification"),
  DIAGNOSTIC_STATUS_NOTIFICATION("DiagnosticsStatusNotification"),
  FIRMWARE_STATUS_NOTIFICATION("FirmwareStatusNotification"),
  HEART_BEAT("Heartbeat"),
  METER_VALUES("MeterValues"),
  STATUS_NOTIFICATION("StatusNotification");

  private String status;

  OcppRequestedMessageEnum(String status) {
    this.status = status;
  }

  public static OcppRequestedMessageEnum get(String status) {
    return Stream.of(OcppRequestedMessageEnum.values())
        .filter(value -> value.equals(status))
        .findFirst()
        .orElse(null);
  }

  public static boolean contains(String codigo) {
    return Stream.of(OcppRequestedMessageEnum.values()).anyMatch(status -> status.equals(codigo));
  }
}
