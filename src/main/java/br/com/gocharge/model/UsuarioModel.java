package br.com.gocharge.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
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

  @Column(name = "NOME", columnDefinition = "VARCHAR(250)", updatable = false, nullable = false)
  private String nome;

  @Column(name = "ENDERECO", columnDefinition = "VARCHAR(250)", updatable = false, nullable = false)
  private String endereco;

  @Column(name = "TEL_RES", columnDefinition = "VARCHAR(10)", updatable = false, nullable = false)
  private String telefoneResidencia;

  @Column(name = "TEL_CEL", columnDefinition = "VARCHAR(11)", updatable = false, nullable = false)
  private String telefoneCelular;

  @Column(name = "EMAIL", columnDefinition = "VARCHAR(250)", updatable = false, nullable = false)
  private String email;

  @ManyToOne(cascade = CascadeType.REFRESH)
  @JoinColumn(
      name = "COD_TP_PESS",
      columnDefinition = "VARCHAR(1)",
      updatable = false,
      nullable = false)
  private TipoPessoaModel tipoPessoa;

  @Column(name = "CPF", columnDefinition = "VARCHAR(11)", updatable = false, nullable = false)
  private String cpf;

  @Column(name = "CNPJ", columnDefinition = "VARCHAR(14)", updatable = false, nullable = false)
  private String cnpj;

  @ManyToOne(cascade = CascadeType.REFRESH)
  @JoinColumn(
      name = "COD_STAT_USU",
      referencedColumnName = "COD_STAT_USU",
      columnDefinition = "VARCHAR(1)",
      nullable = false)
  private StatusUsuarioModel status;

  @Column(name = "SENHA", columnDefinition = "VARCHAR(250)", nullable = false)
  private String senha;

  @Column(name = "DT_HR_BLQ", columnDefinition = "DATETIME")
  private LocalDateTime dataHoraBloqueio;

  @Column(name = "MOT_BLQ", columnDefinition = "VARCHAR(250)")
  private String motivoBloqueio;
}
