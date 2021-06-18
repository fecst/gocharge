package br.com.gocharg.enums;

import lombok.Getter;

import java.util.stream.Stream;

@Getter
public enum StatusTotemEnum {
  LIGADO(1, "Ligado", "TurnOn"),
  DESLIGADO(2, "Desligado", "TurnOff"),
  DISPONIVEL(3, "Disponível", "Available"),
  PREPARANDO(4, "Preparando", "Preparing"),
  CARREGANDO(5, "Carregando", "Charging"),
  EV_SUSPENSO(6, "EV Suspenso", "SuspendedEVSE"),
  EV_SE_SUSPENSO(7, "EV SE Suspenso", "SuspendedEV"),
  FINALIZANDO(8, "Finalizando", "Finishing"),
  RESERVADO(9, "Reservado", "Reserved"),
  INDISPONIVEL(10, "Indisponivel", "Unavailable"),
  QUEBRADO(11, "Quebrado", "Faulted");

  private Integer codigo;
  private String descricao;
  private String description;

  StatusTotemEnum(Integer codigo, String descricao, String description) {
    this.codigo = codigo;
    this.descricao = descricao;
    this.description = description;
  }

  public static StatusTotemEnum get(Integer codigo) {
    return Stream.of(StatusTotemEnum.values())
        .filter(value -> value.getCodigo().equals(codigo))
        .findFirst()
        .orElse(null);
  }

  public static StatusTotemEnum getByDescription(String description) {
    return Stream.of(StatusTotemEnum.values())
        .filter(value -> value.getDescription().equals(description))
        .findFirst()
        .orElse(null);
  }

  public static boolean contains(String codigo) {
    return Stream.of(StatusTotemEnum.values()).anyMatch(status -> status.codigo.equals(codigo));
  }
}
