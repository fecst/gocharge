package br.com.gocharg.enums.ocpp;

import lombok.Getter;

import java.util.stream.Stream;

@Getter
public enum OcppFunctionsEnum {
  BOOT_NOTIFICATION("BootNotification"),
  STATUS_NOTIFICATION("StatusNotification"),
  HEART_BEAT("Heartbeat"),
  AUTHORIZE("Authorize"),
  START_TRANSACTION("StartTransaction"),
  METER_VALUES("MeterValues"),
  STOP_TRANSACTION("StopTransaction"),
  REMOTE_START_TRANSACTION("RemoteStartTransaction"),
  REMOTE_STOP_TRANSACTION("RemoteStopTransaction"),
  RESERVE_NOW("ReserveNow"),
  CANCEL_RESERVATION("CancelReservation"),
  CHANGE_CONFIGURATION("ChangeConfiguration"),
  GET_CONFIGURATION("GetConfiguration"),
  UPDATE_FIRMWARE("UpdateFirmware"),
  FIRMWARE_STATUS_NOTIFICATION("FirmwareStatusNotification"),
  GET_DIAGNOSTICS("GetDiagnostics"),
  DIAGNOSTICS_STATUS_NOTIFICATION("DiagnosticsStatusNotification"),
  CHANGE_AVAILABILITY("ChangeAvailability"),
  CLEAR_CACHE("ClearCache"),
  RESET("Reset"),
  UNLOCK_CONNECTOR("UnlockConnector"),
  SET_CHARGING_PROFILE("SetChargingProfile"),
  GET_COMPOSITE_SCHEDULE("GetCompositeSchedule"),
  CLEAR_CHARGING_PROFILE("ClearChargingProfile"),
  DATA_TRANSFER("DataTransfer"),
  TRIGGER_MESSAGE("TriggerMessage");

  private String funcion;

  OcppFunctionsEnum(String funcion) {
    this.funcion = funcion;
  }

  public static OcppFunctionsEnum get(String status) {
    return Stream.of(OcppFunctionsEnum.values())
        .filter(value -> value.getFuncion().equals(status))
        .findFirst()
        .orElse(null);
  }

  public static boolean contains(String codigo) {
    return Stream.of(OcppFunctionsEnum.values()).anyMatch(status -> status.equals(codigo));
  }
}
