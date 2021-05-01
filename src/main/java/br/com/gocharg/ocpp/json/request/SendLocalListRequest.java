package br.com.gocharg.ocpp.json.request;

import br.com.gocharg.enums.ocpp.OcppUpdateTypeEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SendLocalListRequest {
  private Integer listVersion;
  private LocalAuthorizationList localAuthorizationList;
  private OcppUpdateTypeEnum updateType;
}
