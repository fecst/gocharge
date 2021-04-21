package br.com.gocharge.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class Valor {
    private UUID id;
    private LocalDateTime dataHoraCadastro;
    private Bandeira bandeira;
    private BigDecimal valorKwH;
    private Double percentualAdicional;
    private BigDecimal valorAdicional;
    private String descricao;
}
