package br.com.gocharg.ocpp.json.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StartTransactionResponse {
    private TagInfo idTagInfo;
    private Integer transactionId;
}
