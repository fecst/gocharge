package br.com.gocharg.domain;

import br.com.gocharg.enums.StatusCadastroEnum;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class Bandeira {
    private UUID id;
    private LocalDateTime dataHoraCadastro;
    private String descricao;
    private StatusCadastroEnum status;
}
