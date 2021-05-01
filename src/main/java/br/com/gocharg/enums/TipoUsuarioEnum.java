package br.com.gocharg.enums;

import lombok.Getter;

import java.util.stream.Stream;

@Getter
public enum TipoUsuarioEnum {
  CLIENTE_FINAL_CPF_RESIDENCIAL(1, "Cliente final CPF residencial"),
  CLIENTE_FINAL_CPF_COMPRA(2, "Cliente final CPF compra"),
  CLIENTE_CNPJ(3, "Cliente CNPJ"),
  CLIENTE_FINAL_CNPJ_VENDA(4, "Cliente final CNPJ venda"),
  CLIENTE_FINAL_CNPJ_INTERNO(5, "Cliente final CNPJ interno"),
  CLIENTE_FINAL_CNPJ_COMPRA(6, "Cliente final CNPJ compra"),
  CLIENTE_FINAL_CNPJ_INTERNO_E_COMPRA(7, "Cliente final CNPJ interno e compra"),
  CLIENTE_CNPJ_GESTOR(8, "Cliente CNPJ gestor");

  private Integer id;
  private String descricao;

  TipoUsuarioEnum(Integer id, String descricao) {
    this.id = id;
    this.descricao = descricao;
  }

  public static TipoUsuarioEnum get(Integer id) {
    return Stream.of(TipoUsuarioEnum.values())
        .filter(value -> value.getId().equals(id))
        .findFirst()
        .orElse(null);
  }

  public static boolean contains(String codigo) {
    return Stream.of(TipoUsuarioEnum.values())
        .anyMatch(tipo -> tipo.id.equals(Integer.valueOf(codigo)));
  }
}
