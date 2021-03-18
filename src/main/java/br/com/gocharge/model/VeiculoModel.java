package br.com.gocharge.model;

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
  private LocalDateTime dataHotaCadastro;

  @ManyToOne(cascade = CascadeType.REFRESH)
  @JoinColumn(
      name = "ID_USU",
      referencedColumnName = "ID_USU",
      columnDefinition = "VARCHAR(36)",
      nullable = false,
      updatable = false)
  private UsuarioModel usuarioModel;

  @Column(name = "MARCA", columnDefinition = "VARCHAR(250)")
  private String marca;

  @Column(name = "MODELO", columnDefinition = "VARCHAR(250)")
  private String modelo;

  @Column(name = "APELIDO", columnDefinition = "VARCHAR(250)", nullable = false)
  private String apelido;

  @ManyToOne(cascade = CascadeType.REFRESH)
  @JoinColumn(
      name = "COD_STAT_CAD",
      referencedColumnName = "COD_STAT_CAD",
      columnDefinition = "VARCHAR(1)",
      nullable = false)
  private StatusCadastroModel status;
}
