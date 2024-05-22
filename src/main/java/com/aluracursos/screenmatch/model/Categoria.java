package com.aluracursos.screenmatch.model;

public enum Categoria {
    //reconozca según una categoría específica
    ACCION("Action", "Acción"),
    ROMANCE("Romance", "Romance"),
    COMEDIA("Comedy", "Comedia"),
    DRAMA("Drama", "Drama"),
    CRIMEN("Crime", "Crimen");

    private String categoriaOmdb;
    Categoria (String categoriaOmdb, String categoriaEspanol ){
        this.categoriaOmdb = categoriaOmdb;
        this.categoriaEspanol = categoriaEspanol;
    }
    private  String categoriaEspanol;
    public static Categoria fromString(String text) {
        for (Categoria categoria : Categoria.values()) {
            if (categoria.categoriaOmdb.equalsIgnoreCase(text)) {
                return categoria;
            }
        }
        throw new IllegalArgumentException("Ninguna categoría encontrada: " + text);
    }
    public static Categoria fromEspanol(String text) {
        for (Categoria categoria : Categoria.values()) {
            //verifica el texto es igual y remplaza
            if (categoria.categoriaEspanol.equalsIgnoreCase(text)) {
                return categoria;
            }
        }
        throw new IllegalArgumentException("Ninguna categoría encontrada: " + text);
    }

}
