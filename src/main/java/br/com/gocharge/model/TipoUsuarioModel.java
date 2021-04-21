package br.com.gocharge.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "TP_USU")
@Getter
@Setter
public class TipoUsuarioModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(
            name = "COD_TP_USU",
            columnDefinition = "INT",
            updatable = false,
            unique = true,
            nullable = false)
    private Integer id;

    @Column(name = "DESCRICAO", columnDefinition = "VARCHAR(250)", nullable = false)
    private String descricao;
}
