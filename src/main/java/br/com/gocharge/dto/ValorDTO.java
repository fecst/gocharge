package br.com.gocharge.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

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
  private String valorKwH;

  @JsonProperty("percentual_adicional")
  private String percentualAdicional;

  @JsonProperty("valor_adicional")
  private String valorAdicional;

  @JsonProperty("descricao")
  private String descricao;
}