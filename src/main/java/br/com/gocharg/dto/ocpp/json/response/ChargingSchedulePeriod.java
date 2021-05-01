package br.com.gocharg.dto.ocpp.json.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChargingSchedulePeriod {
  private Integer startPeriod;
  private Number limit;
  private Integer numberPhases;
}
