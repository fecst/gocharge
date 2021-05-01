package br.com.gocharg.enums.ocpp;

import lombok.Getter;

import java.util.stream.Stream;

@Getter
public enum OcppMeasurandEnum {
  ENERGY_ACTIVE_EXPORT_REGISTER("Energy.Active.Export.Register"),
  ENERGY_ACTIVE_IMPORT_REGISTER("Energy.Active.Import.Register"),
  ENERGY_REACTIVE_EXPORT_REGISTER("Energy.Reactive.Export.Register"),
  ENERGY_REACTIVE_IMPORT_REGISTER("Energy.Reactive.Import.Register"),
  ENERGY_ACTIVE_EXPORT_INTERVAL("Energy.Active.Export.Interval"),
  ENERGY_ACTIVE_IMPORT_INTERVAL("Energy.Active.Import.Interval"),
  ENERGY_REACTIVE_EXPORT_INTERVAL("Energy.Reactive.Export.Interval"),
  ENERGY_REACTIVE_IMPORT_INTERVAL("Energy.Reactive.Import.Interval"),
  POWER_ACTIVE_EXPORT("Power.Active.Export"),
  POWER_ACTIVE_IMPORT("Power.Active.Import"),
  POWER_OFFERED("Power.Offered"),
  POWER_REACTIVE_EXPORT("Power.Reactive.Export"),
  POWER_REACTIVE_IMPORT("Power.Reactive.Import"),
  POWER_FACTOR("Power.Factor"),
  CURRENT_IMPORT("Current.Import"),
  CURRENT_EXPORT("Current.Export"),
  CURRENT_OFFERED("Current.Offered"),
  VOLTAGE("Voltage"),
  FREQUENCY("Frequency"),
  TEMPERATURE("Temperature"),
  SOC("SoC"),
  RPM("RPM");

  private String status;

  OcppMeasurandEnum(String status) {
    this.status = status;
  }

  public static OcppMeasurandEnum get(String status) {
    return Stream.of(OcppMeasurandEnum.values())
        .filter(value -> value.equals(status))
        .findFirst()
        .orElse(null);
  }

  public static boolean contains(String codigo) {
    return Stream.of(OcppMeasurandEnum.values()).anyMatch(status -> status.equals(codigo));
  }
}
