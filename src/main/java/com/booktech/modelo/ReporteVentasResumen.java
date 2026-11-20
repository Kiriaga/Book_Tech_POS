package com.booktech.reportes;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Concentra metricas de ventas para un rango de fechas en BookTech POS.
 * Provee validacion de rango, resumen formateado y fila lista para JTable en Java Swing.
 */
public class ReporteVentasResumen {

    private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private final LocalDate fechaInicio;
    private final LocalDate fechaFin;
    private final int totalTransacciones;
    private final double montoTotalVendido;
    private final int totalUnidadesVendidas;

    public ReporteVentasResumen(LocalDate fechaInicio, LocalDate fechaFin,
                                int totalTransacciones, double montoTotalVendido,
                                int totalUnidadesVendidas) {
        if (fechaInicio == null || fechaFin == null) {
            throw new IllegalArgumentException("Las fechas de inicio y fin son obligatorias.");
        }
        if (fechaFin.isBefore(fechaInicio)) {
            throw new IllegalArgumentException("La fecha final no puede ser anterior a la fecha inicial.");
        }
        if (totalTransacciones < 0 || totalUnidadesVendidas < 0) {
            throw new IllegalArgumentException("Las cantidades de transacciones y unidades no pueden ser negativas.");
        }
        if (montoTotalVendido < 0.0 || Double.isNaN(montoTotalVendido) || Double.isInfinite(montoTotalVendido)) {
            throw new IllegalArgumentException("El monto total vendido no puede ser negativo ni invalido.");
        }

        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.totalTransacciones = totalTransacciones;
        this.montoTotalVendido = montoTotalVendido;
        this.totalUnidadesVendidas = totalUnidadesVendidas;
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

    public int getTotalUnidadesVendidas() {
        return totalUnidadesVendidas;
    }

    public double calcularTicketPromedio() {
        if (totalTransacciones <= 0) {
            return 0.0;
        }
        return montoTotalVendido / totalTransacciones;
    }

    public double calcularUnidadesPromedioPorVenta() {
        if (totalTransacciones <= 0) {
            return 0.0;
        }
        return (double) totalUnidadesVendidas / totalTransacciones;
    }

    public String obtenerRangoFormateado() {
        return fechaInicio.format(FORMATO_FECHA) + " - " + fechaFin.format(FORMATO_FECHA);
    }

    public String generarResumenTexto() {
        return String.format(
            "Periodo: %s | Ventas: %d | Unidades: %d | Total: $%.2f | Ticket promedio: $%.2f",
            obtenerRangoFormateado(),
            totalTransacciones,
            totalUnidadesVendidas,
            montoTotalVendido,
            calcularTicketPromedio()
        );
    }

    public Object[] toSummaryRow() {
        return new Object[]{
            obtenerRangoFormateado(),
            totalTransacciones,
            totalUnidadesVendidas,
            String.format("$%.2f", montoTotalVendido),
            String.format("$%.2f", calcularTicketPromedio())
        };
    }
}
