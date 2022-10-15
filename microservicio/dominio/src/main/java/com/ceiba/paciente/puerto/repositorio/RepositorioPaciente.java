package com.ceiba.paciente.puerto.repositorio;

import com.ceiba.paciente.modelo.entidad.Paciente;

public interface RepositorioPaciente {

    Paciente obtener(Long id);

    Long guardar(Paciente paciente);
}
