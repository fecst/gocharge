package br.com.gocharg.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Cidade {
  private Integer id;
  private Estado estado;
  private String descricao;
}
