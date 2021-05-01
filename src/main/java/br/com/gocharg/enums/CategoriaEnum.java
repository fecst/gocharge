package br.com.gocharg.enums;

import lombok.Getter;

import java.util.stream.Stream;

@Getter
public enum CategoriaEnum {
    A("A", "A"),
    B("B", "B"),
    C("C", "C"),
    D("D", "D"),
    E("E", "E");

    private String id;
    private String descricao;

    CategoriaEnum(String id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public static CategoriaEnum get(String codigo) {
        return Stream.of(CategoriaEnum.values())
                .filter(value -> value.getId().equals(codigo))
                .findFirst()
                .orElse(null);
    }

    public static boolean contains(String codigo) {
        return Stream.of(CategoriaEnum.values())
                .anyMatch(categoria -> categoria.id.equals(codigo));
    }
}
