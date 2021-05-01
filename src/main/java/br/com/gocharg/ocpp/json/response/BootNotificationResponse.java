package br.com.gocharg.ocpp.json.response;

import br.com.gocharg.enums.ocpp.OcppResponseStatusEnum;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BootNotificationResponse {
  private OcppResponseStatusEnum status;
  private LocalDateTime currentTime;
  private Integer interval;
}
