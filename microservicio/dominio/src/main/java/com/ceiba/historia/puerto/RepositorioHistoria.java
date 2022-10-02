package com.ceiba.historia.puerto;

import com.ceiba.historia.entidad.Historia;

import java.time.LocalDate;

public interface RepositorioHistoria {

    Object obtenerFechaReciente(Long idPaciente);
}
