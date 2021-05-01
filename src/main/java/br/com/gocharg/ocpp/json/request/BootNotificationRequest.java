package br.com.gocharg.ocpp.json.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BootNotificationRequest {
  private String chargePointVendor;
  private String chargePointModel;
  private String chargePointSerialNumber;
  private String chargeBoxSerialNumber;
  private String firmwareVersion;
  private String iccid;
  private String imsi;
  private String meterType;
  private String meterSerialNumber;
}
