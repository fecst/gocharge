package br.com.gocharg.enums;

import lombok.Getter;

import java.util.stream.Stream;

@Getter
public enum StatusTotemEnum {
  LIGADO(1, "Ligado"),
  DESLIGADO(2, "Desligado"),
  DISPONIVEL(3, "Disponível"),
  PREPARANDO(4, "Preparando"),
  CARREGANDO(5, "Carregando"),
  EV_SUSPENSO(6, "EV Suspenso"),
  EV_SE_SUSPENSO(7, "EV SE Suspenso"),
  FINALIZANDO(8, "Finalizando"),
  RESERVADO(9, "Reservado"),
  INDISPONIVEL(10, "Indisponivel"),
  QUEBRADO(11, "Quebrado");

  private Integer codigo;
  private String descricao;

  StatusTotemEnum(Integer codigo, String descricao) {
    this.codigo = codigo;
    this.descricao = descricao;
  }

  public static StatusTotemEnum get(Integer codigo) {
    return Stream.of(StatusTotemEnum.values())
        .filter(value -> value.getCodigo().equals(codigo))
        .findFirst()
        .orElse(null);
  }

  public static boolean contains(String codigo) {
    return Stream.of(StatusTotemEnum.values()).anyMatch(status -> status.codigo.equals(codigo));
  }
}
