package br.com.gocharge.enums;

import lombok.Getter;

@Getter
public enum StatusCadastroEnum {
  INATIVO("I", "Inativo"),
  ATIVO("A", "Ativo");

  private String codigo;
  private String descricao;

  StatusCadastroEnum(String codigo, String descricao) {
    this.codigo = codigo;
    this.descricao = descricao;
  }
}
