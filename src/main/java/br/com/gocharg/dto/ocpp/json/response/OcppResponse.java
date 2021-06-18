package br.com.gocharg.dto.ocpp.json.response;

import br.com.gocharg.enums.ocpp.OcppFunctionsEnum;
import br.com.gocharg.enums.ocpp.OcppMessageTypeEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OcppResponse {
  private OcppMessageTypeEnum operation;
  private String uniqueID;
  private OcppStatusResponse status;
  private String apelidoTotem;
}
