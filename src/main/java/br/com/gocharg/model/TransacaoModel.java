package br.com.gocharg.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "TRAN_POD")
@Getter
@Setter
public class TransacaoModel {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(
      name = "ID_TRAN",
      columnDefinition = "VARCHAR(36)",
      updatable = false,
      unique = true,
      nullable = false)
  private UUID id;

  @Column(name = "DT_HR_CAD", columnDefinition = "DATETIME", updatable = false, nullable = false)
  private LocalDateTime dataHoraCadastro;

  @Column(name = "OPERACAO", columnDefinition = "INT", nullable = false)
  private Integer operation;

  @Column(name = "ID_UNICO", columnDefinition = "INT", nullable = false)
  private Integer uniqueID;

  @Column(name = "ACAO", columnDefinition = "VARCHAR(250)", nullable = false)
  private String action;

  @Column(name = "PAYLOAD", columnDefinition = "VARCHAR(250)", nullable = false)
  private String payload;

  @ManyToOne
  @JoinColumn(
      name = "ID_TOTEM",
      referencedColumnName = "ID_TOTEM",
      columnDefinition = "VARCHAR(36)",
      nullable = false)
  private TotemModel totem;
}
