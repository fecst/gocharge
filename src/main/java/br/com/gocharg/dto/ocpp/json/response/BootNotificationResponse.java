package br.com.gocharg.dto.ocpp.json.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BootNotificationResponse {
  private String status;
  private String currentTime;
  private Integer interval;
}
