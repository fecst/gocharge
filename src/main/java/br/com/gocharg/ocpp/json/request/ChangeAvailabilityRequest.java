package br.com.gocharg.ocpp.json.request;

import br.com.gocharg.enums.ocpp.OcppAvailabilityEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangeAvailabilityRequest {
  private Integer connectorId;
  private OcppAvailabilityEnum type;
}
