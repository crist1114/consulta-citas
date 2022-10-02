package com.ceiba.cita.modelo.entidad;

import com.ceiba.historia.entidad.Historia;
import com.ceiba.paciente.entidad.Paciente;
import com.ceiba.paciente.entidad.TipoPaciente;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SolicitudAgendar {

    private final Long idPaciente;
    private final String tipoProcedimiento;


}
