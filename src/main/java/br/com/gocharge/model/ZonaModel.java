package br.com.gocharge.model;

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
            name = "ID_ZONA",
            columnDefinition = "VARCHAR(36)",
            updatable = false,
            unique = true,
            nullable = false)
    private UUID id;

    @ManyToOne
    @JoinColumn(
            name = "ID_EST",
            referencedColumnName = "ID_EST",
            columnDefinition = "VARCHAR(36)",
            nullable = false)
    private EstadoModel estado;

    @ManyToOne
    @JoinColumn(
            name = "ID_CID",
            referencedColumnName = "ID_CID",
            columnDefinition = "VARCHAR(36)",
            nullable = false)
    private CidadeModel cidade;

    @Column(name = "DESCRICAO", columnDefinition = "VARCHAR(250)", nullable = false)
    private String descricao;
}
