package br.com.gocharg.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class TotemDTO {

    @JsonProperty("id")
    private UUID id;

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

    @JsonProperty("status")
    private String status;

    @JsonProperty("apelido")
    private String apelido;
}
