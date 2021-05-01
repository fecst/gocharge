package br.com.gocharg.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "CATEG")
@Getter
@Setter
public class CategoriaModel {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(
      name = "COD_CATEG",
      columnDefinition = "VARCHAR(1)",
      updatable = false,
      unique = true,
      nullable = false)
  private String id;

  @Column(name = "DESCRICAO", columnDefinition = "VARCHAR(250)", nullable = false)
  private String descricao;
}
