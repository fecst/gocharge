package br.com.gocharg.ocpp.json.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MeterValuesRequest {
  private Integer connectorId;
  private Integer transactionId;
  private MeterValue meterValue;
}
