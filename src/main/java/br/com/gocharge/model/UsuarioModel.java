package br.com.gocharge.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "CAD_USU")
@Getter
@Setter
public class UsuarioModel {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(
      name = "ID_USU",
      columnDefinition = "VARCHAR(36)",
      updatable = false,
      unique = true,
      nullable = false)
  private UUID id;

  @Column(name = "DT_HR_CAD", columnDefinition = "DATETIME", updatable = false, nullable = false)
  private LocalDateTime dataHoraCadastro;

  @Column(name = "NOME", columnDefinition = "VARCHAR(250)")
  private String nome;

  @Column(name = "LOGIN", columnDefinition = "VARCHAR(250)")
  private String login;

  @Column(name = "EMAIL", columnDefinition = "VARCHAR(250)", nullable = false)
  private String email;

  @Column(name = "CPF", columnDefinition = "VARCHAR(11)")
  private String cpf;

  @Column(name = "CNPJ", columnDefinition = "VARCHAR(14)")
  private String cnpj;

  @Column(name = "DT_NASC", columnDefinition = "DATE")
  private Date dataNascimento;

  @Column(name = "SENHA", columnDefinition = "VARCHAR(250)")
  private String senha;

  @Column(name = "TEL_1", columnDefinition = "VARCHAR(11)")
  private String telefone1;

  @Column(name = "TEL_2", columnDefinition = "VARCHAR(11)")
  private String telefone2;

  @Column(name = "TEL_3", columnDefinition = "VARCHAR(11)")
  private String telefone3;

  @Column(name = "CEP", columnDefinition = "VARCHAR(8)")
  private String cep;

  @Column(name = "ENDERECO", columnDefinition = "VARCHAR(250)")
  private String endereco;

  @Column(name = "NUM_END", columnDefinition = "VARCHAR(10)")
  private String numero;

  @Column(name = "COMPL_END", columnDefinition = "VARCHAR(250)")
  private String complemento;

  @Column(name = "BAIRRO", columnDefinition = "VARCHAR(250)")
  private String bairro;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "ID_CID", referencedColumnName = "ID_CID", columnDefinition = "VARCHAR(36)")
  private CidadeModel cidade;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "ID_EST", referencedColumnName = "ID_EST", columnDefinition = "VARCHAR(36)")
  private EstadoModel estado;

  @Column(name = "PAIS", columnDefinition = "VARCHAR(250)")
  private String pais;

  @ManyToOne(cascade = CascadeType.REFRESH)
  @JoinColumn(name = "COD_TP_USU", referencedColumnName = "COD_TP_USU", columnDefinition = "INT")
  private TipoUsuarioModel tipoUsuario;

  @Column(name = "MAPS", columnDefinition = "TINYINT(1)")
  private Boolean maps;

  @ManyToOne(cascade = CascadeType.REFRESH)
  @JoinColumn(name = "COD_CAT_USU", referencedColumnName = "COD_CAT_USU", columnDefinition = "VARCHAR(1)")
  private CategoriaUsuarioModel categoriaUsuario;

  @Column(name = "OBS_1", columnDefinition = "VARCHAR(65535)")
  private String observacao1;

  @Column(name = "OBS_2", columnDefinition = "VARCHAR(65535)")
  private String observacao2;

  @ManyToOne(cascade = CascadeType.REFRESH)
  @JoinColumn(
      name = "COD_STAT_USU",
      referencedColumnName = "COD_STAT_USU",
      columnDefinition = "VARCHAR(1)",
      nullable = false)
  private StatusUsuarioModel status;

  @Column(name = "DT_HR_BLQ", columnDefinition = "DATETIME")
  private LocalDateTime dataHoraBloqueio;

  @Column(name = "MOT_BLQ", columnDefinition = "VARCHAR(250)")
  private String motivoBloqueio;
}
