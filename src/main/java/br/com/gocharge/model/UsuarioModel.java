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

  @Column(name = "NOME", columnDefinition = "VARCHAR(250)", nullable = false)
  private String nome;

  @Column(name = "APELIDO", columnDefinition = "VARCHAR(250)", nullable = false)
  private String apelido;

  @Column(name = "EMAIL", columnDefinition = "VARCHAR(250)", nullable = false)
  private String email;

  @Column(name = "CPF", columnDefinition = "VARCHAR(11)", updatable = false, nullable = false)
  private String cpf;

  @Column(name = "DT_NASC", columnDefinition = "DATE", updatable = false, nullable = false)
  private String dataNascimento;

  @Column(name = "SENHA", columnDefinition = "VARCHAR(250)", nullable = false)
  private String senha;

  @Column(name = "TEL", columnDefinition = "VARCHAR(11)", nullable = false)
  private String telefone;

  @Column(name = "CEP", columnDefinition = "VARCHAR(8)", nullable = false)
  private String cep;

  @Column(name = "ENDERECO", columnDefinition = "VARCHAR(250)", nullable = false)
  private String endereco;

  @Column(name = "NUM_END", columnDefinition = "VARCHAR(10)", nullable = false)
  private String numero;

  @Column(name = "BAIRRO", columnDefinition = "VARCHAR(250)", nullable = false)
  private String bairro;

  @ManyToOne
  @JoinColumn(
          name = "ID_EST",
          referencedColumnName = "ID_EST",
          columnDefinition = "VARCHAR(36)",
          nullable = false)
  private EstadoModel estado;

  @ManyToOne
  @JoinColumn(
          name = "ID_CID",
          referencedColumnName = "ID_CID",
          columnDefinition = "VARCHAR(36)",
          nullable = false)
  private CidadeModel cidade;

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
