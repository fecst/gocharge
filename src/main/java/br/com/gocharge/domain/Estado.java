package br.com.gocharge.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class Estado {
  private UUID id;
  private String descricao;
}
