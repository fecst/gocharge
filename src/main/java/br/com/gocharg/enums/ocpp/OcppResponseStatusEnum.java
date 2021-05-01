package br.com.gocharg.enums.ocpp;

import lombok.Getter;

import java.util.stream.Stream;

@Getter
public enum OcppResponseStatusEnum {
  ACCEPTED("Accepted"),
  BLOCKED("Blocked"),
  CONCURRENT_TX("ConcurrentTx"),
  EXPIRED("Expired"),
  FAULTED("Faulted"),
  INVALID("Invalid"),
  NOT_IMPLEMENTED("NotImplemented"),
  NOT_SUPORTED("NotSupported"),
  OCCUPIED("Occupied"),
  REJECTED("Rejected"),
  REBOOT_REQUIRED("RebootRequired"),
  SCHEDULED("Scheduled"),
  UNAVAILABLE("Unavailable"),
  UNKNOWN("Unknown"),
  UNKNOWN_MESSAGE_ID("UnknownMessageId"),
  UNKNOWN_VENDOR_ID("UnknownVendorId"),
  UNLOCKED("Unlocked"),
  UNLOCK_FAILED("UnlockFailed");

  private String status;

  OcppResponseStatusEnum(String status) {
    this.status = status;
  }

  public static OcppResponseStatusEnum get(String status) {
    return Stream.of(OcppResponseStatusEnum.values())
        .filter(value -> value.equals(status))
        .findFirst()
        .orElse(null);
  }

  public static boolean contains(String codigo) {
    return Stream.of(OcppResponseStatusEnum.values()).anyMatch(status -> status.equals(codigo));
  }
}
