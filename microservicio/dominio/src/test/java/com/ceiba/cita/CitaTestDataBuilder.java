package com.ceiba.cita;

import com.ceiba.cita.modelo.dto.ResumenCitaDTO;
import com.ceiba.cita.modelo.entidad.Cita;
import com.ceiba.cita.modelo.entidad.EstadoCita;
import com.ceiba.cita.modelo.entidad.TipoProcedimiento;

import java.time.LocalDate;
import java.util.List;

public class CitaTestDataBuilder {

    private Long id;

    private Long idPaciente;

    private TipoProcedimiento tipoProcedimiento;

    private LocalDate fecha;

    private EstadoCita estado;

    private static final int TIEMPO_MAX_VALIDO_HISTORIA_PARA_CITA_MANTENIMIENTO = 3;

    public CitaTestDataBuilder conCitaPorDefecto() {
        this.id = 1l;
        this.idPaciente = Long.valueOf(1090);
        this.tipoProcedimiento = TipoProcedimiento.LIMPIEZA;
        this.fecha = LocalDate.now();
        this.estado = EstadoCita.NO_ATENDIDA;
        return this;
    }

    public CitaTestDataBuilder conId(Long id){
        this.id = id;
        return this;
    }

    public CitaTestDataBuilder conIdPaciente(Long idPaciente){
        this.idPaciente = idPaciente;
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

    public Cita crear(LocalDate fechaHistoria, List<ResumenCitaDTO> citasAgendadas) {
        return Cita.crear(  new SolicitudAgendarTestDataBuilder()
                .conIdPaciente(idPaciente)
                .conTipoProcedimiento(tipoProcedimiento.toString())
                .build(),
                fechaHistoria,
                citasAgendadas
                );
    }

    }
