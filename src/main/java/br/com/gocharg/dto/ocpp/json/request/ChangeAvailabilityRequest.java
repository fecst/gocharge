package br.com.gocharg.dto.ocpp.json.request;

import br.com.gocharg.enums.ocpp.OcppAvailabilityEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangeAvailabilityRequest {
  private Integer connectorId;
  private String type;
}
