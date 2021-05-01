package br.com.gocharg.dto.ocpp.json.request;

import br.com.gocharg.enums.ocpp.OcppResetEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResetRequest {
  private OcppResetEnum type;
}
