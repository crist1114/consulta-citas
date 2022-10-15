package com.ceiba.historia.puerto;


import com.ceiba.historia.modelo.entidad.Historia;

public interface RepositorioHistoria {

    Object obtenerFechaReciente(Long idPaciente);

    Long guardar(Historia historia);
}
