package br.com.gocharg.ocpp.json.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UpdateFirmwareRequest {
  private String location;
  private Integer retries;
  private LocalDateTime retrieveDate;
  private Integer retryInterval;
}
