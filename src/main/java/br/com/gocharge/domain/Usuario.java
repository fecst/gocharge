package br.com.gocharge.domain;

import br.com.gocharge.enums.CategoriaUsuarioEnum;
import br.com.gocharge.enums.StatusUsuarioEnum;
import br.com.gocharge.enums.TipoUsuarioEnum;
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
    private String login;
    private String email;
    private String cpf;
    private String cnpj;
    private Date dataNascimento;
    private String senha;
    private String telefone1;
    private String telefone2;
    private String telefone3;
    private String cep;
    private String endereco;
    private String numero;
    private String complemento;
    private String bairro;
    private Cidade cidade;
    private Estado estado;
    private String pais;
    private TipoUsuarioEnum tipoUsuario;
    private Boolean maps;
    private CategoriaUsuarioEnum categoriaUsuario;
    private String observacao1;
    private String observacao2;
    private StatusUsuarioEnum status;
    private LocalDateTime dataHoraBloqueio;
    private String motivoBloqueio;
}
