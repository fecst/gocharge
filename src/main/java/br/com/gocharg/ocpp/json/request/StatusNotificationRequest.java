package br.com.gocharg.ocpp.json.request;

import br.com.gocharg.enums.ocpp.OcppErrorCodeEnum;
import br.com.gocharg.enums.ocpp.OcppRequestStatusEnum;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class StatusNotificationRequest {
    private Integer connectorId;
    private OcppErrorCodeEnum errorCode;
    private String info;
    private OcppRequestStatusEnum status;
    private LocalDateTime timestamp;
    private String vendorId;
    private String vendorErrorCode;
}
