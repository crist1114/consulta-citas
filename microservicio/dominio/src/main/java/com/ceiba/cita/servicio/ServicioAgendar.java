package com.ceiba.cita.servicio;


import com.ceiba.cita.modelo.entidad.Cita;
import com.ceiba.cita.modelo.entidad.SolicitudAgendar;
import com.ceiba.cita.puerto.repositorio.RepositorioCita;
import com.ceiba.dominio.ValidadorArgumento;
import com.ceiba.historia.puerto.RepositorioHistoria;
import com.ceiba.paciente.puerto.RepositorioPaciente;


public class ServicioAgendar {


    private final RepositorioCita repositorioCita;

    private final RepositorioHistoria repositorioHistoria;

    private final RepositorioPaciente repositorioPaciente;

    public ServicioAgendar(RepositorioCita repositorioCita, RepositorioHistoria repositorioHistoria, RepositorioPaciente repositorioPaciente) {
        this.repositorioCita = repositorioCita;
        this.repositorioHistoria = repositorioHistoria;
        this.repositorioPaciente = repositorioPaciente;
    }

    public Long ejecutar(SolicitudAgendar solicitudAgendar) {

        ValidadorArgumento.validarObligatorio(repositorioPaciente.obtener(solicitudAgendar.getIdPaciente()), "El paciente no existe, debe realizar primero el registro");

        var cita = Cita.crear(solicitudAgendar,
                        repositorioHistoria.obtenerFechaReciente(solicitudAgendar.getIdPaciente())
                        ,repositorioCita.ObtenerCitasAgendadasPaciente(solicitudAgendar.getIdPaciente()));

        return repositorioCita.guardar(cita);
    }
}
