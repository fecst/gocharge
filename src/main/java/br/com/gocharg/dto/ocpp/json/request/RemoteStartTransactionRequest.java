package br.com.gocharg.dto.ocpp.json.request;

import br.com.gocharg.dto.ocpp.json.response.ChargingProfile;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(Include.NON_NULL)
public class RemoteStartTransactionRequest {
  private Integer connectorId;
  private String idTag;
  private ChargingProfile chargingProfile;
}
