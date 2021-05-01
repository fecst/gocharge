package br.com.gocharg.ocpp.json.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GetConfigurationRequest {
  private List<String> key;
}
