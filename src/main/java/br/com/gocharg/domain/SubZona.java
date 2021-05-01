package br.com.gocharg.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class SubZona {
  private UUID id;
  private Estado estado;
  private Cidade cidade;
  private Zona zona;
  private String descricao;
}
