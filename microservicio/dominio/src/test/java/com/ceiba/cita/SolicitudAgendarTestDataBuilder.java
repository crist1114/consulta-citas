package com.ceiba.cita;

import com.ceiba.cita.modelo.entidad.SolicitudAgendar;

public class SolicitudAgendarTestDataBuilder {

    private Long idPaciente;
    private String tipoProcedimiento;

    public SolicitudAgendarTestDataBuilder() {
    }

    public SolicitudAgendarTestDataBuilder conTipoProcedimiento(String tipoProcedimiento){
        this.tipoProcedimiento = tipoProcedimiento;
        return this;
    }

    public SolicitudAgendarTestDataBuilder conIdPaciente(Long idPaciente){
        this.idPaciente = idPaciente;
        return this;
    }

    public SolicitudAgendar build() {
        return new SolicitudAgendar(idPaciente, tipoProcedimiento);
    }
}
