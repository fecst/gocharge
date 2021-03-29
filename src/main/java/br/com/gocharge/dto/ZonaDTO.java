package br.com.gocharge.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ZonaDTO {

  @JsonProperty("id")
  private String id;

  @JsonProperty("estado")
  private String estado;

  @JsonProperty("cidade")
  private String cidade;

  @JsonProperty("descricao")
  private String descricao;
}
