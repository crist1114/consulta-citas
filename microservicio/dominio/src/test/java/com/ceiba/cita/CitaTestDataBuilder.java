package com.ceiba.cita;

import com.ceiba.cita.modelo.entidad.Cita;
import com.ceiba.cita.modelo.entidad.EstadoCita;
import com.ceiba.cita.modelo.entidad.TipoProcedimiento;
import com.ceiba.paciente.PacienteTest;
import com.ceiba.paciente.PacienteTestDataBuilder;
import com.ceiba.paciente.entidad.Paciente;

import java.time.LocalDate;

public class CitaTestDataBuilder {

    private Long id;

    private Paciente paciente;

    private TipoProcedimiento tipoProcedimiento;

    private LocalDate fecha;

    private EstadoCita estado;

    private static final int TIEMPO_MAX_VALIDO_HISTORIA_PARA_CITA_MANTENIMIENTO = 3;

    public CitaTestDataBuilder conCitaPorDefecto() {
        this.id = 1l;
        this.paciente = new PacienteTestDataBuilder().conPacientePorDefecto().reconstruir();
        this.tipoProcedimiento = TipoProcedimiento.LIMPIEZA;
        this.fecha = LocalDate.now();
        this.estado = EstadoCita.NO_ATENDIDA;
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

    public CitaTestDataBuilder conFecha() {
        this.fecha = LocalDate.now();
        return this;
    }

    public CitaTestDataBuilder conEstado() {
        this.estado = EstadoCita.NO_ATENDIDA;
        return this;
    }

    public Cita crear() {
        return Cita.crear( new SolicitudAgendarTestDataBuilder()
                .conPaciente(paciente)
                .conTipoProcedimiento(tipoProcedimiento.toString())
                .build()
                );
    }

    }
