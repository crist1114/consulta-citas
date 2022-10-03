package com.ceiba.cita;

import com.ceiba.cita.modelo.entidad.SolicitudAgendar;
import com.ceiba.paciente.entidad.Paciente;

public class SolicitudAgendarTestDataBuilder {

    private Paciente paciente;
    private String tipoProcedimiento;

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

    public SolicitudAgendar build() {
        return new SolicitudAgendar(paciente, tipoProcedimiento);
    }
}
