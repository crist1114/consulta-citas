package com.ceiba.cita.puerto.dao;

import com.ceiba.cita.modelo.dto.ResumenCitaDTO;

import java.time.LocalDate;
import java.util.List;

public interface DaoCita {

    ResumenCitaDTO obtenerCitaNoAtendidaPaciente(Long idPaciente);

    List<ResumenCitaDTO> obtenerCitas();

    List<ResumenCitaDTO> obtenerCitasPorFecha(String fecha);
}
