package com.ceiba.cita.servicio;


import com.ceiba.cita.modelo.entidad.Cita;
import com.ceiba.cita.modelo.entidad.SolicitudAgendar;
import com.ceiba.cita.modelo.entidad.TipoProcedimiento;
import com.ceiba.cita.puerto.repositorio.RepositorioCita;
import com.ceiba.dominio.ValidadorArgumento;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.historia.puerto.RepositorioHistoria;

import java.math.BigDecimal;
import java.sql.SQLOutput;


public class ServicioAgendar {


    private final RepositorioCita repositorioCita;

    private final RepositorioHistoria repositorioHistoria;

    public ServicioAgendar(RepositorioCita repositorioCita, RepositorioHistoria repositorioHistoria) {
        this.repositorioCita = repositorioCita;
        this.repositorioHistoria = repositorioHistoria;
    }

    public Long ejecutar(SolicitudAgendar solicitudAgendar) {

        var cita = Cita.crear(solicitudAgendar);
        BigDecimal valorCita = cita.getValorPorTipo(solicitudAgendar.getTipoPaciente());

        elMontoEsMenor(valorCita, solicitudAgendar.getValor());
        pacienteYaTieneCita(cita.getIdPaciente());
        citaNoValidaParaMantenimiento(cita);

        return repositorioCita.guardar(cita);
    }

    private void elMontoEsMenor(BigDecimal valorCita, BigDecimal valorPagado) {
        double diferencia = diferencia(valorCita, valorPagado);

        if(diferencia < 0){
            throw new ExcepcionValorInvalido("El monto es menor por "+diferencia*(-1)+ "$");
        }
    }

    private double diferencia(BigDecimal valorCita, BigDecimal valorPagado){return valorPagado.subtract(valorCita).doubleValue();}

    private void pacienteYaTieneCita(Long id){

        if(repositorioCita.obtenerCitasAgendadasPaciente(id)>0)
            throw new ExcepcionValorInvalido("El paciente ya tiene cita agendada");
    }

    private void citaNoValidaParaMantenimiento(Cita cita){
        var fechaUltimaHistoria = repositorioHistoria.obtenerFechaReciente(cita.getIdPaciente());
        if(cita.esMantenimientoDeBrackets()
                && !cita.historiaValidaParaCitaMantenimiento(fechaUltimaHistoria)) {
            throw new ExcepcionValorInvalido("Debe agendar cita para limpieza ya que su ultima historia registrada es mayor a 3 meses");
        }
    }


}
