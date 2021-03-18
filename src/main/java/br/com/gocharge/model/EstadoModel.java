package br.com.gocharge.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "CAD_EST")
@Getter
@Setter
public class EstadoModel {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(
      name = "ID_EST",
      columnDefinition = "VARCHAR(36)",
      updatable = false,
      unique = true,
      nullable = false)
  private UUID id;

  @Column(name = "DESCRICAO", columnDefinition = "VARCHAR(250)")
  private String descricao;
}
