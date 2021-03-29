package br.com.gocharge.domain;

import br.com.gocharge.enums.StatusUsuarioEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Usuario {
  private UUID id;
  private LocalDateTime dataHoraCadastro;
  private String nome;
  private String apelido;
  private String email;
  private String cpf;
  private Date dataNascimento;
  private String senha;
  private String telefone;
  private String endereco;
  private String cep;
  private String numero;
  private String bairro;
  private Cidade cidade;
  private Estado estado;
  private StatusUsuarioEnum status;
  private LocalDateTime dataHoraBloqueio;
  private String motivoBloqueio;
}
