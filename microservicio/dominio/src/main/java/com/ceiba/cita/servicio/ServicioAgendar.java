package com.ceiba.cita.servicio;


import com.ceiba.cita.modelo.entidad.Cita;
import com.ceiba.cita.modelo.entidad.SolicitudAgendar;
import com.ceiba.cita.modelo.entidad.TipoProcedimiento;
import com.ceiba.cita.puerto.repositorio.RepositorioCita;
import com.ceiba.dominio.ValidadorArgumento;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.historia.puerto.RepositorioHistoria;


public class ServicioAgendar {


    private final RepositorioCita repositorioCita;

    private final RepositorioHistoria repositorioHistoria;

    public ServicioAgendar(RepositorioCita repositorioCita, RepositorioHistoria repositorioHistoria) {
        this.repositorioCita = repositorioCita;
        this.repositorioHistoria = repositorioHistoria;
    }

    public Long ejecutar(SolicitudAgendar solicitudAgendar) {

        var cita = Cita.crear(solicitudAgendar);

        var fechaUltimaHistoria = repositorioHistoria.obtenerFechaReciente(cita.getIdPaciente());
        Long totalDeCitas = repositorioCita.obtenerCitasAgendadasPaciente(cita.getIdPaciente());

        if(totalDeCitas>0)
            throw new ExcepcionValorInvalido("El paciente ya tiene cita agendada");

        if(cita.esMantenimientoDeBrackets()
         && !cita.historiaValidaParaCitaMantenimiento(fechaUltimaHistoria))
            throw new ExcepcionValorInvalido("Debe agendar cita para limpieza ya que su ultima historia registrada es mayor a 3 meses");

        return repositorioCita.guardar(cita);
    }
}
