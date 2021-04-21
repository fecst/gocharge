package br.com.gocharge.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class Zona {
    private UUID id;
    private Estado estado;
    private Cidade cidade;
    private String descricao;
}
