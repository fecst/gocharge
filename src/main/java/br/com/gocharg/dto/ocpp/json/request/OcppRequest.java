package br.com.gocharg.dto.ocpp.json.request;

import br.com.gocharg.enums.ocpp.OcppFunctionsEnum;
import br.com.gocharg.enums.ocpp.OcppMessageTypeEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OcppRequest<T> {
  private OcppMessageTypeEnum operation;
  private String uniqueID;
  private OcppFunctionsEnum action;
  private T payload;
}
