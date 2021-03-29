package br.com.gocharge.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VeiculoDTO {

  @JsonProperty("id")
  private String id;

  @JsonProperty("data_hora_cadastro")
  private String dataHoraCadastro;

  @JsonProperty("usuario")
  private String usuario;

  @JsonProperty("marca")
  private String marca;

  @JsonProperty("modelo")
  private String modelo;

  @JsonProperty("apelido")
  private String apelido;

  @JsonProperty("status")
  private String status;
}
