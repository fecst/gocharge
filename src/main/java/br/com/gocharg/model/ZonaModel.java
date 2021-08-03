package br.com.gocharg.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "CAD_ZONA")
@Getter
@Setter
public class ZonaModel {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(
      name = "COD_ZONA",
      columnDefinition = "VARCHAR(36)",
      updatable = false,
      unique = true,
      nullable = false)
  private UUID id;

  @ManyToOne
  @JoinColumn(
      name = "COD_EST",
      referencedColumnName = "COD_EST",
      columnDefinition = "VARCHAR(2)",
      nullable = false)
  private EstadoModel estado;

  @ManyToOne
  @JoinColumn(
      name = "COD_CID",
      referencedColumnName = "COD_CID",
      columnDefinition = "VARCHAR(36)",
      nullable = false)
  private CidadeModel cidade;

  @Column(name = "DESCRICAO", columnDefinition = "VARCHAR(250)", nullable = false)
  private String descricao;
}
