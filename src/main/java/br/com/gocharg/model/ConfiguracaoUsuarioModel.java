package br.com.gocharg.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "CONF_USU")
@Getter
@Setter
public class ConfiguracaoUsuarioModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(
            name = "ID_CONF",
            columnDefinition = "VARCHAR(36)",
            updatable = false,
            unique = true,
            nullable = false)
    private UUID id;

    @Column(name = "DT_HR_CAD", columnDefinition = "DATETIME", updatable = false, nullable = false)
    private LocalDateTime dataHoraCadastro;

    @OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(
            name = "ID_USU",
            referencedColumnName = "ID_USU",
            columnDefinition = "VARCHAR(36)",
            nullable = false)
    private UsuarioModel usuarioModel;

    @Column(name = "DUPL_VALID", columnDefinition = "TINYINT(1)", nullable = false)
    private Boolean duplaValidacao;
}
