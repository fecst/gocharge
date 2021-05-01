package br.com.gocharg.dto.ocpp.json.request;

import br.com.gocharg.enums.ocpp.OcppReasonEnum;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class StopTransactionRequest {
  private String idTag;
  private Integer meterStop;
  private LocalDateTime timestamp;
  private Integer transactionId;
  private OcppReasonEnum reason;
  private TransactionData transactionData;
}
