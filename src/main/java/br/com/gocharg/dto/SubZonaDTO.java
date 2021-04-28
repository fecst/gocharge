package br.com.gocharg.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubZonaDTO {

    @JsonProperty("id")
    private String id;

    @JsonProperty("estado")
    private String estado;

    @JsonProperty("cidade")
    private String cidade;

    @JsonProperty("zona")
    private String zona;

    @JsonProperty("sub_zona")
    private String descricao;
}
