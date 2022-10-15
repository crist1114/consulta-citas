package com.ceiba.historia.modelo.entidad;

import com.ceiba.paciente.modelo.entidad.Paciente;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
@AllArgsConstructor
@Getter
public class SolicitudRegistrarHistoria {

    private Paciente paciente;
    private LocalDate fechaEmision;
    private Ubicaciones ubicacion;


}
