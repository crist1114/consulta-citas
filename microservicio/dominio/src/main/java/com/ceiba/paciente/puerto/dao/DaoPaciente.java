package com.ceiba.paciente.puerto.dao;

import com.ceiba.paciente.modelo.dto.ResumenPacienteDTO;

import java.util.List;

public interface DaoPaciente {

    public ResumenPacienteDTO obtenerPaciente(Long id);

    List<ResumenPacienteDTO> obtenerPacientes();
}
