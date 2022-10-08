package com.ceiba.cita.puerto.dao;

import com.ceiba.cita.modelo.dto.ResumenCitaDTO;

public interface DaoCita {

    ResumenCitaDTO obtenerCitaNoAtendidaPaciente(Long idPaciente);
}
