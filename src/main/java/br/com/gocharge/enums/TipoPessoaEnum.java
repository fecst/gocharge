package br.com.gocharge.enums;

import lombok.Getter;

@Getter
public enum TipoPessoaEnum {
  PESSOA_JURIDICA("J", "Pessoa Jurídica"),
  PESSOA_FISICA("F", "Pessoa Física");

  private String codigo;
  private String descricao;

  TipoPessoaEnum(String codigo, String descricao) {
    this.codigo = codigo;
    this.descricao = descricao;
  }
}
