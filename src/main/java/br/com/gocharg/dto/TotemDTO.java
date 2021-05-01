package br.com.gocharg.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TotemDTO {

  @JsonProperty("id")
  private String id;

  @JsonProperty("data_hora_cadastro")
  private String dataHoraCadastro;

  @JsonProperty("estado")
  private String estado;

  @JsonProperty("cidade")
  private String cidade;

  @JsonProperty("zona")
  private String zona;

  @JsonProperty("sub_zona")
  private String subZona;

  @JsonProperty("valor")
  private String valor;

  @JsonProperty("fabricante")
  private String fabricante;

  @JsonProperty("status")
  private String status;

  @JsonProperty("apelido")
  private String apelido;

  @JsonProperty("maps")
  private String maps;

  @JsonProperty("propriedade_go_charg")
  private String propriedadeGoCharg;

  @JsonProperty("proprietario")
  private String proprietario;

  @JsonProperty("qr_code")
  private String qrCode;

  @JsonProperty("categoria")
  private String categoria;
}
