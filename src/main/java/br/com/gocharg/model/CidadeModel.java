package br.com.gocharg.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "CAD_CID")
@Getter
@Setter
public class CidadeModel {
    @Id
    @Column(
            name = "ID_CID",
            columnDefinition = "INT",
            updatable = false,
            unique = true,
            nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(
            name = "ID_EST",
            referencedColumnName = "ID_EST",
            columnDefinition = "VARCHAR(2)",
            nullable = false)
    private EstadoModel estado;

    @Column(name = "DESCRICAO", columnDefinition = "VARCHAR(250)", nullable = false)
    private String descricao;
}
