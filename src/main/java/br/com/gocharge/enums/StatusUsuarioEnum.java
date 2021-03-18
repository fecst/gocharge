package br.com.gocharge.enums;

import lombok.Getter;

@Getter
public enum StatusUsuarioEnum {
  INATIVO("I", "Inativo"),
  ATIVO("A", "Ativo"),
  BLOQUEADO("B", "Bloqueado");

  private String codigo;
  private String descricao;

  StatusUsuarioEnum(String codigo, String descricao) {
    this.codigo = codigo;
    this.descricao = descricao;
  }
}
