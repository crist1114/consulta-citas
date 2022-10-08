package com.ceiba.paciente.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResumenPacienteDTO {

    private Long id;
    private String nombre;
    private String tipoPaciente;
}
