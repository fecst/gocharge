package br.com.gocharg.enums.ocpp;

import lombok.Getter;

import java.util.stream.Stream;

@Getter
public enum OcppFirmwareStatusEnum {
  DOWNLOADED("Downloaded"),
  DOWNLOAD_FAILED("DownloadFailed"),
  DOWNLOADING("Downloading"),
  IDLE("Idle"),
  INSTALLATION_FAILED("InstallationFailed"),
  INSTALLING("Installing"),
  INSTALLED("Installed");

  private String status;

  OcppFirmwareStatusEnum(String status) {
    this.status = status;
  }

  public static OcppFirmwareStatusEnum get(String status) {
    return Stream.of(OcppFirmwareStatusEnum.values())
        .filter(value -> value.equals(status))
        .findFirst()
        .orElse(null);
  }

  public static boolean contains(String codigo) {
    return Stream.of(OcppFirmwareStatusEnum.values()).anyMatch(status -> status.equals(codigo));
  }
}
