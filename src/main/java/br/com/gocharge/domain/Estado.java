package br.com.gocharge.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class Estado {
  @JsonProperty("id")
  private UUID id;

  @JsonProperty("descricao")
  private String descricao;
}
