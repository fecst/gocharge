package br.com.gocharg.ocpp.json.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetConfigurationResponse {
  private ConfigurationKey configurationKey;
  private String unknownKey;
}
