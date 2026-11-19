package com.booktech.modelo;

import java.util.Objects;

/**
 * Representa un titulo de libro dentro del catalogo de BookTech POS.
 * Disenado para capas de negocio, DAO y futura visualizacion en Java Swing.
 */
public class Libro {

    private String isbn;
    private String titulo;
    private String autor;
    private String categoria;
    private double precioUnitario;
    private int stockDisponible;
    private boolean activo;

    public Libro(String isbn, String titulo, String autor, String categoria,
                 double precioUnitario, int stockDisponible) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.categoria = categoria;
        this.precioUnitario = precioUnitario;
        this.stockDisponible = stockDisponible;
        this.activo = true;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getCategoria() {
        return categoria;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public int getStockDisponible() {
        return stockDisponible;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public double calcularValorInventario() {
        return precioUnitario * stockDisponible;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Libro)) return false;
        Libro libro = (Libro) o;
        return Objects.equals(isbn, libro.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn);
    }
}
