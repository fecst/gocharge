package br.com.gocharg.dto.ocpp.json.response;

import br.com.gocharg.enums.ocpp.OcppChargingRateUnitEnum;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ChargingSchedule {
  private Integer duration;
  private LocalDateTime startSchedule;
  private OcppChargingRateUnitEnum chargingRateUnit;
  private ChargingSchedulePeriod chargingSchedulePeriod;
  private Number minChargingRate;
}
