package br.com.gocharg.domain;

import br.com.gocharg.enums.StatusCadastroEnum;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class Veiculo {
  private UUID id;
  private LocalDateTime dataHoraCadastro;
  private Usuario usuario;
  private String marca;
  private String modelo;
  private String ano;
  private Boolean eletrico;
  private String apelido;
  private StatusCadastroEnum status;
  private String observaocao1;
  private String observaocao2;
}
