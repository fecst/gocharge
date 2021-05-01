package br.com.gocharg.dto.ocpp.json.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class StartTransactionRequest {
  private Integer connectorId;
  private String idTag;
  private Integer meterStart;
  private Integer reservationId;
  private LocalDateTime timestamp;
}
