package com.ceiba.cita.modelo.entidad;

import com.ceiba.paciente.entidad.Paciente;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SolicitudAgendar {

    private final Paciente paciente;
    private final String tipoProcedimiento;
}
