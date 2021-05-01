package br.com.gocharg.dto.ocpp.json.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TransactionData {
  private LocalDateTime timestamp;
  private SampledValue sampledValue;
}
