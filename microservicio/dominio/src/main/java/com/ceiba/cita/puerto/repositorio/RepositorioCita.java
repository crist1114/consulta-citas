package com.ceiba.cita.puerto.repositorio;
import com.ceiba.cita.modelo.entidad.Cita;

import java.util.List;

public interface RepositorioCita {

    Cita obtener(Long id);

    Long guardar(Cita cita);

    Long obtenerCitasAgendadasPaciente(Long id_paciente);
}
