package com.ceiba.cita;

import com.ceiba.cita.modelo.entidad.SolicitudAgendar;
import com.ceiba.paciente.entidad.Paciente;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

public class SolicitudAgendarTestDataBuilder {

    private Paciente paciente;
    private String tipoProcedimiento;
    private LocalDate fecha;
    private LocalTime hora;
    private BigDecimal valorPagado;

    public SolicitudAgendarTestDataBuilder() {
    }

    public SolicitudAgendarTestDataBuilder conTipoProcedimiento(String tipoProcedimiento){
        this.tipoProcedimiento = tipoProcedimiento;
        return this;
    }

    public SolicitudAgendarTestDataBuilder conPaciente(Paciente paciente){
        this.paciente = paciente;
        return this;
    }

    public SolicitudAgendarTestDataBuilder conFecha(LocalDate fecha){
        this.fecha = fecha;
        return this;
    }

    public SolicitudAgendarTestDataBuilder conHora(LocalTime hora){
        this.hora = hora;
        return this;
    }

    public SolicitudAgendarTestDataBuilder conValorPagado(BigDecimal valor){
        this.valorPagado = valor;
        return this;
    }

    public SolicitudAgendar build() {
        return new SolicitudAgendar(paciente, tipoProcedimiento,fecha,hora,valorPagado);
    }
}
