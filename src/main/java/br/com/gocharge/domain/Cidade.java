package br.com.gocharge.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class Cidade {
  private Integer id;
  private Estado estado;
  private String descricao;
}
