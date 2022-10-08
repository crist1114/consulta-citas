package com.ceiba.cita;

import com.ceiba.cita.modelo.entidad.Cita;
import com.ceiba.cita.modelo.entidad.EstadoCita;
import com.ceiba.cita.modelo.entidad.TipoProcedimiento;
import com.ceiba.paciente.PacienteTestDataBuilder;
import com.ceiba.paciente.entidad.Paciente;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

public class CitaTestDataBuilder {

    private Long id;

    private Paciente paciente;

    private TipoProcedimiento tipoProcedimiento;

    private LocalDate fecha;
    private LocalTime hora;

    private EstadoCita estado;

    private static final int TIEMPO_MAX_VALIDO_HISTORIA_PARA_CITA_MANTENIMIENTO = 3;

    private BigDecimal valorPagado;

    public CitaTestDataBuilder conCitaPorDefecto() {
        this.id = 1l;
        this.paciente = new PacienteTestDataBuilder().conPacientePorDefecto().reconstruir();
        this.tipoProcedimiento = TipoProcedimiento.LIMPIEZA;
        this.fecha = LocalDate.now();
        this.estado = EstadoCita.NO_ATENDIDA;
        this.valorPagado = new BigDecimal(55000);
        return this;
    }

    public CitaTestDataBuilder conId(Long id){
        this.id = id;
        return this;
    }

    public CitaTestDataBuilder conPaciente(Paciente paciente){
        this.paciente = paciente;
        return this;
    }

    public CitaTestDataBuilder conTipoProcedimiento(TipoProcedimiento tipoProcedimiento) {
        this.tipoProcedimiento = tipoProcedimiento;
        return this;
    }

    public CitaTestDataBuilder conValorPagado(double valor){
        this.valorPagado = new BigDecimal(valor);
        return this;
    }

    public CitaTestDataBuilder conFecha(LocalDate fecha){
        this.fecha = fecha;
        return this;
    }

    public CitaTestDataBuilder conHora(LocalTime hora){
        this.hora = hora;
        return this;
    }

    public Cita crear() {
        return Cita.crear( new SolicitudAgendarTestDataBuilder()
                .conPaciente(paciente)
                .conTipoProcedimiento(tipoProcedimiento.toString())
                        .conFecha(fecha)
                        .conHora(hora)
                        .conValorPagado(valorPagado)
                .build()
                );
    }

    }
