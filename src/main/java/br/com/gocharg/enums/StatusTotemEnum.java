package br.com.gocharg.enums;

import lombok.Getter;

import java.util.stream.Stream;

@Getter
public enum StatusTotemEnum {
  INDISPONIVEL("I", "Indisponível"),
  DISPONIVEL("D", "Disponível"),
  CARREGANDO("C", "Carregando"),
  BLOQUEADO("B", "Bloqueado");

  private String codigo;
  private String descricao;

  StatusTotemEnum(String codigo, String descricao) {
    this.codigo = codigo;
    this.descricao = descricao;
  }

  public static StatusTotemEnum get(String codigo) {
    return Stream.of(StatusTotemEnum.values())
        .filter(value -> value.getCodigo().equals(codigo))
        .findFirst()
        .orElse(null);
  }

  public static boolean contains(String codigo) {
    return Stream.of(StatusTotemEnum.values()).anyMatch(status -> status.codigo.equals(codigo));
  }
}
