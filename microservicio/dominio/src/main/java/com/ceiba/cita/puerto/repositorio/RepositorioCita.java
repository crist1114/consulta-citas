package com.ceiba.cita.puerto.repositorio;
import com.ceiba.cita.modelo.entidad.Cita;


public interface RepositorioCita {

    Cita obtener(Long id);

    Long guardar(Cita cita);

    Long obtenerCitasAgendadasPaciente(Long idPaciente);
}
