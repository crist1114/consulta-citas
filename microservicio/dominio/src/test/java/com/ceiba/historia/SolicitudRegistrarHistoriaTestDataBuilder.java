package com.ceiba.historia;

import com.ceiba.historia.modelo.entidad.SolicitudRegistrarHistoria;
import com.ceiba.historia.modelo.entidad.Ubicaciones;
import com.ceiba.paciente.modelo.entidad.Paciente;

import java.time.LocalDate;

public class SolicitudRegistrarHistoriaTestDataBuilder {

    private Paciente paciente;
    private LocalDate fechaEmision;
    private Ubicaciones ubicacion;

    public SolicitudRegistrarHistoriaTestDataBuilder() {
    }

    public SolicitudRegistrarHistoriaTestDataBuilder conPaciente(Paciente paciente){
        this.paciente = paciente;
        return this;

    }

    public SolicitudRegistrarHistoriaTestDataBuilder confechaEmision(LocalDate fechaEmision){
        this.fechaEmision = fechaEmision;
        return this;
    }

    public SolicitudRegistrarHistoriaTestDataBuilder conUbicacion(Ubicaciones ubicacion){
        this.ubicacion = ubicacion;
        return this;
    }

    public SolicitudRegistrarHistoria build(){
        return new SolicitudRegistrarHistoria(paciente, fechaEmision,ubicacion);
    }
}
