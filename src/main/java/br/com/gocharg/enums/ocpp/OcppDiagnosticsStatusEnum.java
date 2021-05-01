package br.com.gocharg.enums.ocpp;

import lombok.Getter;

import java.util.stream.Stream;

@Getter
public enum OcppDiagnosticsStatusEnum {
  IDLE("Idle"),
  UPLOADED("Uploaded"),
  UPLOAD_FAILED("UploadFailed"),
  UPLOADING("Uploading");

  private String status;

  OcppDiagnosticsStatusEnum(String status) {
    this.status = status;
  }

  public static OcppDiagnosticsStatusEnum get(String status) {
    return Stream.of(OcppDiagnosticsStatusEnum.values())
        .filter(value -> value.equals(status))
        .findFirst()
        .orElse(null);
  }

  public static boolean contains(String codigo) {
    return Stream.of(OcppDiagnosticsStatusEnum.values()).anyMatch(status -> status.equals(codigo));
  }
}
