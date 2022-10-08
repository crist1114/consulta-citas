package com.ceiba.cita.modelo.entidad;

import com.ceiba.paciente.entidad.Paciente;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@AllArgsConstructor
public class SolicitudAgendar {

    private final Paciente paciente;
    private final String tipoProcedimiento;
    private final LocalDate fecha;
    private final LocalTime hora;
    private final BigDecimal valor;

    public String getTipoPaciente(){return this.paciente.getTipoPaciente().toString();}

}
