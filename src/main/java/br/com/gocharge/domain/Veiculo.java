package br.com.gocharge.domain;

import br.com.gocharge.enums.StatusCadastroEnum;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class Veiculo {
  private UUID id;
  private LocalDateTime dataHotaCadastro;
  private Usuario usuario;
  private String marca;
  private String modelo;
  private String apelido;
  private StatusCadastroEnum status;
}
