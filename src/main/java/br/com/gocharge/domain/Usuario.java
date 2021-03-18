package br.com.gocharge.domain;

import br.com.gocharge.enums.StatusUsuarioEnum;
import br.com.gocharge.enums.TipoPessoaEnum;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class Usuario {
  private UUID id;
  private LocalDateTime dataHoraCadastro;
  private String nome;
  private String endereco;
  private String telefoneResidencia;
  private String telefoneCelular;
  private String email;
  private TipoPessoaEnum tipoPessoa;
  private String cpf;
  private String cnpj;
  private StatusUsuarioEnum status;
  private String senha;
  private LocalDateTime dataHoraBloqueio;
  private String motivoBloqueio;
}
