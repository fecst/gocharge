package br.com.gocharg.ocpp.json.response;

import br.com.gocharg.enums.ocpp.OcppChargingProfileKindEnum;
import br.com.gocharg.enums.ocpp.OcppProfilePurposeEnum;
import br.com.gocharg.enums.ocpp.OcppRecurrencyKindEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChargingProfile {
  private Integer chargingProfileId;
  private Integer transactionId;
  private Integer stackLevel;
  private OcppProfilePurposeEnum chargingProfilePurpose;
  private OcppChargingProfileKindEnum chargingProfileKind;
  private OcppRecurrencyKindEnum recurrencyKind;
  private String validFrom;
  private String validTo;
  private ChargingSchedule chargingSchedule;
}
