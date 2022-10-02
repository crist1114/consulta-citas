package com.ceiba.paciente.puerto.dao;

import com.ceiba.paciente.entidad.Paciente;
import com.ceiba.paciente.entidad.ResumenPacienteDTO;

public interface DaoPaciente {

    ResumenPacienteDTO obtenerPaciente(Long id);
}
