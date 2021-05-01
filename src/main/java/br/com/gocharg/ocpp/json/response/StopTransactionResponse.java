package br.com.gocharg.ocpp.json.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StopTransactionResponse {
    private TagInfo idTagInfo;
}
