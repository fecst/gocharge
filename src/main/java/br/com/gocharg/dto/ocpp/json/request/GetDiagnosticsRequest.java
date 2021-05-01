package br.com.gocharg.dto.ocpp.json.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetDiagnosticsRequest {
  private String location;
  private Integer retries;
  private Integer retryInterval;
  private String startTime;
  private String stopTime;
}
