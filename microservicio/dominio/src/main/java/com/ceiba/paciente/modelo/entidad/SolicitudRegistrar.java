package com.ceiba.paciente.modelo.entidad;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SolicitudRegistrar {

    private Long id;
    private String nombre;
    private String tipoPaciente;
}
