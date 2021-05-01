package br.com.gocharg.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CAD_EST")
@Getter
@Setter
public class EstadoModel {
  @Id
  @Column(
      name = "ID_EST",
      columnDefinition = "VARCHAR(2)",
      updatable = false,
      unique = true,
      nullable = false)
  private String id;

  @Column(name = "DESCRICAO", columnDefinition = "VARCHAR(250)")
  private String descricao;
}
