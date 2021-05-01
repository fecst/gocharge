package br.com.gocharg.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "CAD_VEIC")
@Getter
@Setter
public class VeiculoModel {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(
      name = "ID_VEIC",
      columnDefinition = "VARCHAR(36)",
      updatable = false,
      unique = true,
      nullable = false)
  private UUID id;

  @Column(name = "DT_HR_CAD", columnDefinition = "DATETIME", updatable = false, nullable = false)
  private LocalDateTime dataHoraCadastro;

  @ManyToOne(cascade = CascadeType.REFRESH)
  @JoinColumn(
      name = "ID_USU",
      referencedColumnName = "ID_USU",
      columnDefinition = "VARCHAR(36)",
      nullable = false,
      updatable = false)
  private UsuarioModel usuario;

  @Column(name = "MARCA", columnDefinition = "VARCHAR(250)")
  private String marca;

  @Column(name = "MODELO", columnDefinition = "VARCHAR(250)")
  private String modelo;

  @Column(name = "ANO", columnDefinition = "VARCHAR(4)")
  private String ano;

  @Column(name = "ELETRICO", columnDefinition = "TINYINT(1)")
  private Boolean eletrico;

  @Column(name = "APELIDO", columnDefinition = "VARCHAR(250)", nullable = false)
  private String apelido;

  @ManyToOne(cascade = CascadeType.REFRESH)
  @JoinColumn(
      name = "COD_STAT_CAD",
      referencedColumnName = "COD_STAT_CAD",
      columnDefinition = "VARCHAR(1)",
      nullable = false)
  private StatusCadastroModel status;

  @Column(name = "OBS_1", columnDefinition = "VARCHAR(65535)")
  private String observacao1;

  @Column(name = "OBS_2", columnDefinition = "VARCHAR(65535)")
  private String observacao2;
}
