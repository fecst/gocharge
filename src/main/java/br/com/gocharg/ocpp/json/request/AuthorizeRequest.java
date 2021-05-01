package br.com.gocharg.ocpp.json.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthorizeRequest {
  private String idTag;
}
