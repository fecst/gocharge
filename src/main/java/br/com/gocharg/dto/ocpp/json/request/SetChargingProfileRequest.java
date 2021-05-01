package br.com.gocharg.dto.ocpp.json.request;

import br.com.gocharg.dto.ocpp.json.response.ChargingProfile;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SetChargingProfileRequest {
  private Integer connectorId;
  private ChargingProfile csChargingProfiles;
}
