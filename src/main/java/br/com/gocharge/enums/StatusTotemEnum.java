package br.com.gocharge.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public enum StatusTotemEnum {
  INATIVO("I", "Indisponível"),
  LIBERADO("L", "Liberado"),
  CARREGANDO("C", "Carregando"),
  BLOQUEADO("B", "Bloqueado");

  private String codigo;
  private String descricao;

  StatusTotemEnum(String codigo, String descricao) {
    this.codigo = codigo;
    this.descricao = descricao;
  }
}
