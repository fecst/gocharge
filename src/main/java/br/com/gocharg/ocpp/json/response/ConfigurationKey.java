package br.com.gocharg.ocpp.json.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConfigurationKey {
  private String key;
  private Boolean readonly;
  private String value;
}
