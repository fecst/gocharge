package br.com.gocharg.enums.ocpp;

import lombok.Getter;

import java.util.stream.Stream;

@Getter
public enum OcppMessageTypeEnum {
  CALL(2),
  CALL_RESULT(3),
  CALL_ERROR(4);

  private Integer type;

  OcppMessageTypeEnum(Integer type) {
    this.type = type;
  }

  public static OcppMessageTypeEnum get(Integer status) {
    return Stream.of(OcppMessageTypeEnum.values())
        .filter(value -> value.getType().equals(status))
        .findFirst()
        .orElse(null);
  }

  public static boolean contains(String codigo) {
    return Stream.of(OcppMessageTypeEnum.values()).anyMatch(status -> status.equals(codigo));
  }
}
