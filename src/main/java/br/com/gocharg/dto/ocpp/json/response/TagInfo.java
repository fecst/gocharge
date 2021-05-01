package br.com.gocharg.dto.ocpp.json.response;

import br.com.gocharg.enums.ocpp.OcppResponseStatusEnum;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TagInfo {
  private LocalDateTime expiryDate;
  private String parentIdTag;
  private OcppResponseStatusEnum status;
}
