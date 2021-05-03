package br.com.gocharg.dto.ocpp.json.request;

import br.com.gocharg.enums.ocpp.OcppErrorCodeEnum;
import br.com.gocharg.enums.ocpp.OcppRequestStatusEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class StatusNotificationRequest {
  private Integer connectorId;

  private OcppErrorCodeEnum errorCode;

  private String info;

  private OcppRequestStatusEnum status;

  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
  private Date timestamp;

  private String vendorId;

  private String vendorErrorCode;
}
