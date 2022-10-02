package com.ceiba.paciente.puerto;

import com.ceiba.paciente.entidad.Paciente;

public interface RepositorioPaciente {

    Paciente obtener(Long id);

    Long guardar(Paciente paciente);
}
