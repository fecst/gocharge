package br.com.gocharg.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ValorDTO {

  @JsonProperty("id")
  private String id;

  @JsonProperty("data_hora_cadastro")
  private String dataHoraCadastro;

  @JsonProperty("bandeira")
  private String bandeira;

  @JsonProperty("valor_kwh")
  private BigDecimal valorKwH;

  @JsonProperty("percentual_adicional")
  private Double percentualAdicional;

  @JsonProperty("valor_adicional")
  private BigDecimal valorAdicional;

  @JsonProperty("descricao")
  private String descricao;
}
