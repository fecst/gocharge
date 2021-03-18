package br.com.gocharge.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class ConfiguracaoUsuario {
  private UUID id;
  private LocalDateTime dataHotaCadastro;
  private Usuario usuario;
  private Boolean duplaValidacao;
}
