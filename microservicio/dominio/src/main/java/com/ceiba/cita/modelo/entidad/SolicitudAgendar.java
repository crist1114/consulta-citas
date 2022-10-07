package com.ceiba.cita.modelo.entidad;

import com.ceiba.paciente.entidad.Paciente;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class SolicitudAgendar {

    private final Paciente paciente;
    private final String tipoProcedimiento;
    private final BigDecimal valor;

    public String getTipoPaciente(){return this.paciente.getTipoPaciente().toString();}
}
