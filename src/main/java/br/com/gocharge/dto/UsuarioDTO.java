package br.com.gocharge.dto;

import br.com.gocharge.enums.CategoriaUsuarioEnum;
import br.com.gocharge.enums.TipoUsuarioEnum;
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

  @JsonProperty("login")
  private String login;

  @JsonProperty("email")
  private String email;

  @JsonProperty("cpf")
  private String cpf;

  @JsonProperty("cnpj")
  private String cnpj;

  @JsonProperty("data_nascimento")
  private String dataNascimento;

  @JsonProperty("senha")
  private String senha;

  @JsonProperty("telefone_1")
  private String telefone1;

  @JsonProperty("telefone_2")
  private String telefone2;

  @JsonProperty("telefone_3")
  private String telefone3;

  @JsonProperty("cep")
  private String cep;

  @JsonProperty("endereco")
  private String endereco;

  @JsonProperty("numero")
  private String numero;

  @JsonProperty("complemento")
  private String complemento;

  @JsonProperty("bairro")
  private String bairro;

  @JsonProperty("cidade")
  private String cidade;

  @JsonProperty("estado")
  private String estado;

  @JsonProperty("pais")
  private String pais;

  @JsonProperty("tipo_usuario")
  private String tipoUsuario;

  @JsonProperty("maps")
  private Boolean maps;

  @JsonProperty("categoria_usuario")
  private String categoriaUsuario;

  @JsonProperty("status")
  private String status;

  @JsonProperty("data_hora_bloqueio")
  private String dataHoraBloqueio;

  @JsonProperty("motivo_bloqueio")
  private String motivoBloqueio;
}
