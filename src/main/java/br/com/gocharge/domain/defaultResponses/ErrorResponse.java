package br.com.gocharge.domain.defaultResponses;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ErrorResponse {

    @JsonProperty("codigo")
    private String code;

    @JsonProperty("mensagem")
    private String message;

    @JsonProperty("campos")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<ErrorField> fields;

    public ErrorResponse() {super();}

    public ErrorResponse(final String code, final String message, final List<ErrorField> fields) {
        this.code = code;
        this.message = message;
        this.fields = fields;
    }

    public ErrorResponse code(final String code) {
        this.code = code;
        return this;
    }

    public ErrorResponse message(final String message) {
        this.message = message;
        return this;
    }

    public ErrorResponse fields(final List<ErrorField> fields){
        this.fields = fields;
        return this;
    }

    public ErrorResponse field(final ErrorField field) {
        if (fields == null){
            fields = new ArrayList<>();
        }

        fields.add(field);
        return this;
    }

}
