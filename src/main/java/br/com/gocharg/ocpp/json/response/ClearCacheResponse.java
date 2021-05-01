package br.com.gocharg.ocpp.json.response;

import br.com.gocharg.enums.ocpp.OcppResponseStatusEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClearCacheResponse {
    private OcppResponseStatusEnum status;
}
