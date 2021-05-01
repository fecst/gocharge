package br.com.gocharg.ocpp.json.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OcppRequest {
  private Integer operation;
  private String uniqueID;
  private String action;
  private String payload;
}
