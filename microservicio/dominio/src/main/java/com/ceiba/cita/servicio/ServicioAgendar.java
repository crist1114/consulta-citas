package com.ceiba.cita.servicio;


import com.ceiba.cita.modelo.entidad.Cita;
import com.ceiba.cita.modelo.entidad.SolicitudAgendar;
import com.ceiba.cita.puerto.repositorio.RepositorioCita;
import com.ceiba.historia.puerto.RepositorioHistoria;


public class ServicioAgendar {


    private final RepositorioCita repositorioCita;

    private final RepositorioHistoria repositorioHistoria;

    public ServicioAgendar(RepositorioCita repositorioCita, RepositorioHistoria repositorioHistoria) {
        this.repositorioCita = repositorioCita;
        this.repositorioHistoria = repositorioHistoria;
    }

    public Long ejecutar(SolicitudAgendar solicitudAgendar) {

        var cita = Cita.crear(solicitudAgendar,
                        repositorioHistoria.obtenerFechaReciente(solicitudAgendar.getIdPaciente())
                        ,repositorioCita.ObtenerCitasAgendadasPaciente(solicitudAgendar.getIdPaciente()));

        return repositorioCita.guardar(cita);
    }
}
