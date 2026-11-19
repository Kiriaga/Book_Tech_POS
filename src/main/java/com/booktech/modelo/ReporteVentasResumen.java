package com.booktech.reportes;

import java.time.LocalDate;

/**
 * Concentra metricas de ventas para un periodo consultado en BookTech POS.
 * Estructura lista para alimentar tablas y paneles en Java Swing.
 */
public class ReporteVentasResumen {

    private final LocalDate fechaInicio;
    private final LocalDate fechaFin;
    private final int totalTransacciones;
    private final double montoTotalVendido;

    public ReporteVentasResumen(LocalDate fechaInicio, LocalDate fechaFin,
                                int totalTransacciones, double montoTotalVendido) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.totalTransacciones = totalTransacciones;
        this.montoTotalVendido = montoTotalVendido;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public int getTotalTransacciones() {
        return totalTransacciones;
    }

    public double getMontoTotalVendido() {
        return montoTotalVendido;
    }

    public double calcularTicketPromedio() {
        if (totalTransacciones <= 0) {
            return 0.0;
        }
        return montoTotalVendido / totalTransacciones;
    }
}
