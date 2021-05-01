package br.com.gocharg.dto.ocpp.json.response;

import br.com.gocharg.enums.ocpp.OcppResponseStatusEnum;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class GetCompositeScheduleResponse {
  private OcppResponseStatusEnum status;
  private Integer connectorId;
  private LocalDateTime scheduleStart;
  private ChargingSchedule chargingSchedule;
}
