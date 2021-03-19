package br.com.gocharge.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "CAD_VALOR")
@Getter
@Setter
public class ValorModel {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(
      name = "ID_VALOR",
      columnDefinition = "VARCHAR(36)",
      updatable = false,
      unique = true,
      nullable = false)
  private UUID id;

  @Column(name = "DT_HR_CAD", columnDefinition = "DATETIME", updatable = false, nullable = false)
  private LocalDateTime dataHoraCadastro;

  @ManyToOne(cascade = CascadeType.REFRESH)
  @JoinColumn(
      name = "ID_BAND",
      referencedColumnName = "ID_BAND",
      columnDefinition = "VARCHAR(36)",
      nullable = false)
  private BandeiraModel bandeira;

  @Column(name = "VALOR_KWH", columnDefinition = "NUMERIC(9,2)", nullable = false)
  private BigDecimal valorKwH;

  @Column(name = "PERC_ADIC", columnDefinition = "NUMERIC(2,2)", nullable = false)
  private Double percentualAdicional;

  @Column(name = "VALOR_ADIC", columnDefinition = "NUMERIC(9,2)", nullable = false)
  private BigDecimal valorAdicional;

  @Column(name = "DESCRICAO", columnDefinition = "VARCHAR(250)", nullable = false)
  private String descricao;
}
