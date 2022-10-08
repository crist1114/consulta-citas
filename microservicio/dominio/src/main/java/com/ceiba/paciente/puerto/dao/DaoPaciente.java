package com.ceiba.paciente.puerto.dao;

import com.ceiba.paciente.dto.ResumenPacienteDTO;

public interface DaoPaciente {

    public ResumenPacienteDTO obtenerPaciente(Long id);
}
