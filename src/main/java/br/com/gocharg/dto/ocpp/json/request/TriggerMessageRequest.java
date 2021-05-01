package br.com.gocharg.dto.ocpp.json.request;

import br.com.gocharg.enums.ocpp.OcppRequestedMessageEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TriggerMessageRequest {
  private OcppRequestedMessageEnum requestedMessage;
  private Integer connectorId;
}
