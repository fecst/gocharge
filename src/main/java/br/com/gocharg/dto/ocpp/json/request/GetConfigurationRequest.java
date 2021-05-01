package br.com.gocharg.dto.ocpp.json.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GetConfigurationRequest {
  private List<String> key;
}
