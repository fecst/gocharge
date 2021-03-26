package br.com.gocharge.domain;

import br.com.gocharge.enums.StatusUsuarioEnum;
import br.com.gocharge.enums.TipoPessoaEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Usuario {

  @JsonProperty("id")
  private UUID id;

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
  private Cidade cidade;

  @JsonProperty("estado")
  private Estado estado;

  @JsonProperty("status")
  private String status;

  @JsonProperty("data_hora_bloqueio")
  private String dataHoraBloqueio;

  @JsonProperty("motivo_bloqueio")
  private String motivoBloqueio;

  private boolean isCreate;
}
