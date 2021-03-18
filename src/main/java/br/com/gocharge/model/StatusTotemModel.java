package br.com.gocharge.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "STAT_TOTEM")
@Getter
@Setter
public class StatusTotemModel {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(
      name = "COD_STAT_TOTEM",
      columnDefinition = "VARCHAR(36)",
      updatable = false,
      unique = true,
      nullable = false)
  private UUID id;

  @Column(name = "DESCRICAO", columnDefinition = "VARCHAR(250)", nullable = false)
  private String descricao;
}
