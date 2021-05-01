package br.com.gocharg.dto.ocpp.json.request;

import br.com.gocharg.dto.ocpp.json.response.TagInfo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LocalAuthorizationList {
  private String idTag;
  private TagInfo idTagInfo;
}
