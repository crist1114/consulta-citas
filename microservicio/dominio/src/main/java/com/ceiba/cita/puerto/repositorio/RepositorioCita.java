package com.ceiba.cita.puerto.repositorio;
import com.ceiba.cita.modelo.entidad.Cita;

import java.time.LocalDate;
import java.time.LocalTime;


public interface RepositorioCita {

    Cita obtener(Long id);

    Long guardar(Cita cita);

    Long obtenerCitasAgendadasPaciente(Long idPaciente);

    Cita obtenerCitaPorFechaYHora(LocalDate fecha, LocalTime hora);

    void actualizarEstado(Cita cita);
}
