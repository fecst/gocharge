package br.com.gocharge.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "STAT_CAD")
@Getter
@Setter
public class StatusCadastroModel {

  @Id
  @Column(
      name = "COD_STAT_CAD",
      columnDefinition = "VARCHAR(1)",
      updatable = false,
      unique = true,
      nullable = false)
  private String id;

  @Column(name = "DESCRICAO", columnDefinition = "VARCHAR(250)", nullable = false)
  private String descricao;
}
