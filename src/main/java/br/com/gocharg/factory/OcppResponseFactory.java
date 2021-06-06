package br.com.gocharg.factory;

import br.com.gocharg.dto.ocpp.json.request.OcppRequest;
import br.com.gocharg.enums.ocpp.OcppMessageTypeEnum;
import org.springframework.stereotype.Component;

@Component
public class OcppResponseFactory {
  public String retorno(String uniqueID, String payload) {
    String retorno = new String();

    retorno = retorno.concat("[");
    retorno = retorno.concat(OcppMessageTypeEnum.CALL_RESULT.getType().toString());
    retorno = retorno.concat(", \"");
    retorno = retorno.concat(uniqueID);
    retorno = retorno.concat("\", ");
    retorno = retorno.concat(payload);
    retorno = retorno.concat("]");

    return retorno;
  }

  public String requisicao(String uniqueID, String transaction, String payload) {
    String retorno = new String();

    retorno = retorno.concat("[");
    retorno = retorno.concat(OcppMessageTypeEnum.CALL.getType().toString());
    retorno = retorno.concat(", \"");
    retorno = retorno.concat(uniqueID);
    retorno = retorno.concat("\", \"");
    retorno = retorno.concat(transaction);
    retorno = retorno.concat("\", ");
    retorno = retorno.concat(payload);
    retorno = retorno.concat("]");

    return retorno;
  }
}
