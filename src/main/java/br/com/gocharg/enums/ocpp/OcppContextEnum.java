package br.com.gocharg.enums.ocpp;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

import java.util.stream.Stream;

@Getter
public enum OcppContextEnum {
  INTERRUPTION_BEGIN("Interruption.Begin"),
  INTERRUPTION_END("Interruption.End"),
  SAMPLE_CLOCK("Sample.Clock"),
  SAMPLE_PERIODIC("Sample.Periodic"),
  TRANSACTION_BEGIN("Transaction.Begin"),
  TRANSACTION_END("Transaction.End"),
  TRIGGER("Trigger"),
  OTHER("Other");

  private String status;

  OcppContextEnum(String status) {
    this.status = status;
  }

  @JsonCreator
  public static OcppContextEnum forValue(String value) {
    return OcppContextEnum.get(value);
  }

  public static OcppContextEnum get(String status) {
    return Stream.of(OcppContextEnum.values())
        .filter(value -> value.equals(status))
        .findFirst()
        .orElse(null);
  }

  public static boolean contains(String codigo) {
    return Stream.of(OcppContextEnum.values()).anyMatch(status -> status.equals(codigo));
  }
}
