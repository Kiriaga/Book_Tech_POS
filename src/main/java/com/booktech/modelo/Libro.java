package com.booktech.modelo;

import java.util.Objects;

/**
 * Representa un titulo de libro dentro del catalogo de BookTech POS.
 * Incluye validaciones de dominio, control de existencias y formato para Java Swing.
 */
public class Libro {

    private static final int UMBRAL_STOCK_BAJO = 5;

    private final String isbn;
    private String titulo;
    private String autor;
    private String categoria;
    private double precioUnitario;
    private int stockDisponible;
    private boolean activo;

    public Libro(String isbn, String titulo, String autor, String categoria,
                 double precioUnitario, int stockDisponible) {
        this.isbn = validarTextoNoVacio(isbn, "El ISBN es obligatorio.");
        setTitulo(titulo);
        setAutor(autor);
        setCategoria(categoria);
        setPrecioUnitario(precioUnitario);
        setStockDisponible(stockDisponible);
        this.activo = true;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public final void setTitulo(String titulo) {
        this.titulo = validarTextoNoVacio(titulo, "El titulo del libro es obligatorio.");
    }

    public String getAutor() {
        return autor;
    }

    public final void setAutor(String autor) {
        this.autor = validarTextoNoVacio(autor, "El autor del libro es obligatorio.");
    }

    public String getCategoria() {
        return categoria;
    }

    public final void setCategoria(String categoria) {
        this.categoria = validarTextoNoVacio(categoria, "La categoria del libro es obligatoria.");
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public final void setPrecioUnitario(double precioUnitario) {
        if (precioUnitario < 0.0 || Double.isNaN(precioUnitario) || Double.isInfinite(precioUnitario)) {
            throw new IllegalArgumentException("El precio unitario no puede ser negativo ni invalido.");
        }
        this.precioUnitario = precioUnitario;
    }

    public int getStockDisponible() {
        return stockDisponible;
    }

    public final void setStockDisponible(int stockDisponible) {
        if (stockDisponible < 0) {
            throw new IllegalArgumentException("El stock disponible no puede ser negativo.");
        }
        this.stockDisponible = stockDisponible;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public boolean tieneStockBajo() {
        return activo && stockDisponible <= UMBRAL_STOCK_BAJO;
    }

    public String obtenerEstadoLegible() {
        if (!activo) {
            return "Inactivo";
        }
        return tieneStockBajo() ? "Stock Bajo" : "Disponible";
    }

    public double calcularValorInventario() {
        if (!activo) {
            return 0.0;
        }
        return precioUnitario * stockDisponible;
    }

    public Object[] toTableRow() {
        return new Object[]{
            isbn,
            titulo,
            autor,
            categoria,
            String.format("$%.2f", precioUnitario),
            stockDisponible,
            obtenerEstadoLegible()
        };
    }

    private String validarTextoNoVacio(String valor, String mensajeError) {
        if (valor == null || valor.trim().isEmpty()) {
            throw new IllegalArgumentException(mensajeError);
        }
        return valor.trim();
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
