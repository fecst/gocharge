package br.com.gocharg.dto.ocpp.json.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MeterValuesRequest {
  private Integer connectorId;
  private Integer transactionId;
  private List<MeterValue> meterValue;
}
