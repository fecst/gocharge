package br.com.gocharg.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FabricanteDTO {

  @JsonProperty("id")
  private String id;

  @JsonProperty("data_hora_cadastro")
  private String dataHoraCadastro;

  @JsonProperty("descricao")
  private String descricao;

  @JsonProperty("status")
  private String status;
}
