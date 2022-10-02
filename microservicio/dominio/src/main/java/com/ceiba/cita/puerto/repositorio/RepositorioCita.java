package com.ceiba.cita.puerto.repositorio;

import com.ceiba.cita.modelo.dto.ResumenCitaDTO;
import com.ceiba.cita.modelo.entidad.Cita;

import java.util.List;

public interface RepositorioCita {

    Cita obtener(Long id);

    Long guardar(Cita cita);

    List<ResumenCitaDTO> ObtenerCitasAgendadasPaciente(Long id_paciente);
}
