package br.com.gocharg.dto.ocpp.json.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class HeartbeatResponse {
  private String currentTime;
}
