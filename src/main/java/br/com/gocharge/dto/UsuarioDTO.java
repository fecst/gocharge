package br.com.gocharge.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDTO {

  @JsonProperty("id")
  private String id;

  @JsonProperty("data_hora_cadastro")
  private String dataHoraCadastro;

  @JsonProperty("nome")
  private String nome;

  @JsonProperty("apelido")
  private String apelido;

  @JsonProperty("email")
  private String email;

  @JsonProperty("cpf")
  private String cpf;

  @JsonProperty("data_nascimento")
  private String dataNascimento;

  @JsonProperty("senha")
  private String senha;

  @JsonProperty("telefone")
  private String telefone;

  @JsonProperty("endereco")
  private String endereco;

  @JsonProperty("cep")
  private String cep;

  @JsonProperty("numero_endereco")
  private String numero;

  @JsonProperty("bairro")
  private String bairro;

  @JsonProperty("cidade")
  private String cidade;

  @JsonProperty("estado")
  private String estado;

  @JsonProperty("status")
  private String status;

  @JsonProperty("data_hora_bloqueio")
  private String dataHoraBloqueio;

  @JsonProperty("motivo_bloqueio")
  private String motivoBloqueio;
}
