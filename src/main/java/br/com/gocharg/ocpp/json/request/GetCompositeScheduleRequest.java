package br.com.gocharg.ocpp.json.request;

import br.com.gocharg.enums.ocpp.OcppChargingRateUnitEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetCompositeScheduleRequest {
  private Integer connectorId;
  private Integer duration;
  private OcppChargingRateUnitEnum chargingRateUnit;
}
