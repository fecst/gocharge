package br.com.gocharg.dto.ocpp.json.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
public class StartTransactionRequest {
  private Integer connectorId;

  private String idTag;

  private Integer meterStart;

  private Integer reservationId;

  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
  private Date timestamp;
}
