package com.ceiba.historia.servicio;
import com.ceiba.historia.modelo.entidad.Historia;
import com.ceiba.historia.modelo.entidad.SolicitudRegistrarHistoria;
import com.ceiba.historia.puerto.RepositorioHistoria;

public class ServicioRegistrarHistoria {

    RepositorioHistoria repositorioHistoria;

    public ServicioRegistrarHistoria(RepositorioHistoria repositorioHistoria) {
        this.repositorioHistoria = repositorioHistoria;
    }

    public Long ejecutar(SolicitudRegistrarHistoria solicitudRegistrarHistoria){

        Historia historia = Historia.crear(solicitudRegistrarHistoria);

        return repositorioHistoria.guardar(historia);
    }
}
