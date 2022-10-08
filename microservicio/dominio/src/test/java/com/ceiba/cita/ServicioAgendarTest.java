package com.ceiba.cita;

import com.ceiba.BasePrueba;
import com.ceiba.cita.modelo.entidad.Cita;
import com.ceiba.cita.modelo.entidad.SolicitudAgendar;
import com.ceiba.cita.modelo.entidad.TipoProcedimiento;
import com.ceiba.cita.puerto.repositorio.RepositorioCita;
import com.ceiba.cita.servicio.ServicioAgendar;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.historia.puerto.RepositorioHistoria;
import com.ceiba.paciente.PacienteTestDataBuilder;
import com.ceiba.paciente.entidad.Paciente;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

public class ServicioAgendarTest {

    private BigDecimal VALOR_CONTRIBUTIVO = new BigDecimal(55000);

    @Test
    void deberiaGenerarCitaYGuardar(){

        Paciente paciente = new PacienteTestDataBuilder()
                .conPacientePorDefecto().reconstruir();

        var solicitudAgendar = new SolicitudAgendarTestDataBuilder()
                .conPaciente(paciente)
                .conTipoProcedimiento(TipoProcedimiento.LIMPIEZA.toString())
                .conFecha(LocalDate.now().plusDays(2))
                .conHora(LocalTime.of(15, 00,00))
                .conValorPagado(VALOR_CONTRIBUTIVO)
                .build();

        var repositorioCita = Mockito.mock(RepositorioCita.class);
        var repositorioHistoria = Mockito.mock(RepositorioHistoria.class);

        Mockito.when(repositorioCita.guardar(Mockito.any())).thenReturn(1l);

        var servicioAgendar = new ServicioAgendar(repositorioCita, repositorioHistoria);

        var idCitaAgendada = servicioAgendar.ejecutar(solicitudAgendar);

        ArgumentCaptor<Cita> captorCita = ArgumentCaptor.forClass(Cita.class);
        Mockito.verify(repositorioCita, Mockito.times(1)).guardar(captorCita.capture());
        Assertions.assertEquals(1l, idCitaAgendada);
        Assertions.assertEquals(paciente.getId(), captorCita.getValue().getIdPaciente());
        Assertions.assertEquals(TipoProcedimiento.LIMPIEZA, captorCita.getValue().getTipoProcedimiento());
        Assertions.assertEquals(VALOR_CONTRIBUTIVO, captorCita.getValue().getValor());
    }

    @Test
    void historiaNoValidaParaCitaMantenimientoBracketsDeberiaLanzarExcepcion(){

        LocalDate fecha = LocalDate.now().minusMonths(4);

        Paciente paciente = new PacienteTestDataBuilder()
                .conPacientePorDefecto().reconstruir();

        SolicitudAgendar solicitud = new SolicitudAgendarTestDataBuilder()
                .conPaciente(paciente)
                .conTipoProcedimiento(TipoProcedimiento.MANTENIMIENTO_DE_BRACKETS.toString())
                .conFecha(LocalDate.now().plusDays(2))
                .conHora(LocalTime.of(15, 00,00))
                .conValorPagado(new BigDecimal(55000))
                .build();

        RepositorioCita repositorioCita = Mockito.mock(RepositorioCita.class);
        RepositorioHistoria repositorioHistoria = Mockito.mock(RepositorioHistoria.class);

        Mockito.when(repositorioCita.guardar(Mockito.any())).thenReturn(1l);
        Mockito.when(repositorioHistoria.obtenerFechaReciente(Mockito.any())).thenReturn(fecha);

        var servicioAgendar = new ServicioAgendar(repositorioCita, repositorioHistoria);

        BasePrueba.assertThrows(()->
                servicioAgendar.ejecutar(solicitud),
                ExcepcionValorInvalido.class,
                "Debe agendar cita para limpieza ya que su ultima historia registrada es mayor a 3 meses"
                );

    }

    @Test
    void guardarCitaSiPacienteYaTieneCitaDeberiaLanzarExcepcion(){

        Paciente paciente = new PacienteTestDataBuilder()
                .conPacientePorDefecto().reconstruir();

        SolicitudAgendar solicitud = new SolicitudAgendarTestDataBuilder()
                .conPaciente(paciente)
                .conTipoProcedimiento(TipoProcedimiento.MANTENIMIENTO_DE_BRACKETS.toString())
                .conFecha(LocalDate.now().plusDays(2))
                .conHora(LocalTime.of(15, 00,00))
                .conValorPagado(new BigDecimal(55000))
                .build();

        RepositorioCita repositorioCita = Mockito.mock(RepositorioCita.class);
        RepositorioHistoria repositorioHistoria = Mockito.mock(RepositorioHistoria.class);

        Mockito.when(repositorioCita.guardar(Mockito.any())).thenReturn(1l);
        Mockito.when(repositorioCita.obtenerCitasAgendadasPaciente(Mockito.any())).thenReturn(2l);

        var servicioAgendar = new ServicioAgendar(repositorioCita, repositorioHistoria);

        BasePrueba.assertThrows(()->
                        servicioAgendar.ejecutar(solicitud),
                ExcepcionValorInvalido.class,
                "El paciente ya tiene cita agendada"
        );

    }


}
