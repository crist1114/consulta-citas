package com.ceiba.paciente.puerto.dao;

import com.ceiba.paciente.modelo.dto.ResumenPacienteDTO;

public interface DaoPaciente {

    public ResumenPacienteDTO obtenerPaciente(Long id);
}
