package com.ceiba.cita.servicio;

import com.ceiba.cita.modelo.entidad.*;
import com.ceiba.cita.puerto.repositorio.RepositorioCita;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.historia.puerto.RepositorioHistoria;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;


public class ServicioAgendar {

    private final int SABADO = 6;
    private final int DOMINGO = 7;
    private final int HORA_INICIO = 7;
    private final int HORA_FIN = 16;
    private static final int CANTIDAD_DIAS_HABILES = 5;
    private final RepositorioCita repositorioCita;
    private final RepositorioHistoria repositorioHistoria;

    public ServicioAgendar(RepositorioCita repositorioCita, RepositorioHistoria repositorioHistoria) {
        this.repositorioCita = repositorioCita;
        this.repositorioHistoria = repositorioHistoria;
    }

    public Long ejecutar(SolicitudAgendar solicitudAgendar) {

        var cita = Cita.crear(solicitudAgendar);

        pacienteYaTieneCita(cita.getIdPaciente());
        citaNoValidaParaMantenimiento(cita);
        BigDecimal valorCita = cita.getValorPorTipo(solicitudAgendar.getTipoPaciente());
        elMontoEsMenor(valorCita, solicitudAgendar.getValor());
        validarDiaHabil(solicitudAgendar.getFecha());
        validarHoraHabil(solicitudAgendar.getHora());
        validarCupo(solicitudAgendar.getFecha(), solicitudAgendar.getHora());

        return repositorioCita.guardar(cita);
    }

    private void validarHoraHabil(LocalTime hora) {
        if(hora.getHour() < HORA_INICIO || hora.getHour() > HORA_FIN)
            throw new ExcepcionValorInvalido("Hora no se encuentra dentro del horario laboral");
    }

    private void validarDiaHabil(LocalDate fecha) {

        if(fecha.getDayOfWeek().getValue() == SABADO || fecha.getDayOfWeek().getValue() == DOMINGO)
            throw new ExcepcionValorInvalido("Dia no laboral");

        if(!estaDentroDeProximosCincoDias(fecha))
            throw new ExcepcionValorInvalido("Debe seleccionar un dia habil dentro de los proximos 5 dias");
    }

    private boolean estaDentroDeProximosCincoDias(LocalDate fecha) {
        int contadorDias=0;
        LocalDate dia = LocalDate.now();

        while(contadorDias < CANTIDAD_DIAS_HABILES) {

            int diaMes = dia.getDayOfMonth();
            int posicionDia = dia.getDayOfWeek().getValue();

            if(posicionDia != DOMINGO && posicionDia != SABADO) {
                if(fecha.getDayOfMonth() == diaMes){
                    return true;
                }
                contadorDias++;
            }
            dia = dia.plusDays(1);
        }
        return false;
    }

    private void validarCupo(LocalDate fecha, LocalTime hora){
        if(repositorioCita.obtenerCitaPorFechaYHora(fecha, hora) != null){
            throw new ExcepcionValorInvalido("Ya existe una cita a esa hora");
        }
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
