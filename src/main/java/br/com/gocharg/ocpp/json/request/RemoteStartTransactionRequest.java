package br.com.gocharg.ocpp.json.request;

import br.com.gocharg.ocpp.json.response.ChargingProfile;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RemoteStartTransactionRequest {
  private Integer connectorId;
  private String idTag;
  private ChargingProfile chargingProfile;
}
