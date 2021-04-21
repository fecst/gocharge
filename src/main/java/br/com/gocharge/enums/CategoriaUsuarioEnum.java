package br.com.gocharge.enums;

import lombok.Getter;

import java.util.stream.Stream;

@Getter
public enum CategoriaUsuarioEnum {
    A("A", "A"),
    B("B", "B"),
    C("C", "C"),
    D("D", "D"),
    E("E", "E");

    private String id;
    private String descricao;

    CategoriaUsuarioEnum(String id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public static CategoriaUsuarioEnum get(String codigo) {
        return Stream.of(CategoriaUsuarioEnum.values())
                .filter(value -> value.getId().equals(codigo))
                .findFirst()
                .orElse(null);
    }

    public static boolean contains(String codigo) {
        return Stream.of(CategoriaUsuarioEnum.values())
                .anyMatch(categoria -> categoria.id.equals(codigo));
    }
}
