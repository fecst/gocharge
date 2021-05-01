package br.com.gocharg.dto.ocpp.json.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UnlockConnectorRequest {
  private Integer connectorId;
}
