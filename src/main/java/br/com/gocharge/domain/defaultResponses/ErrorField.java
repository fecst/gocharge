package br.com.gocharge.domain.defaultResponses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorField {

    @JsonProperty("campo")
    private String field;

    @JsonProperty("mensagem")
    private String message;

    @JsonProperty("valor")
    private String value;

}
