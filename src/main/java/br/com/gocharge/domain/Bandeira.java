package br.com.gocharge.domain;

import br.com.gocharge.enums.StatusCadastroEnum;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class Bandeira {
  private UUID id;
  private String dataHoraCadastro;
  private String descricao;
  private String status;
}
