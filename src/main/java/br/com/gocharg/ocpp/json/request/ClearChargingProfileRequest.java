package br.com.gocharg.ocpp.json.request;

import br.com.gocharg.enums.ocpp.OcppAvailabilityEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClearChargingProfileRequest {
  private Integer id;
  private Integer connectorId;
  private OcppAvailabilityEnum chargingProfilePurpose;
  private Integer stackLevel;
}
