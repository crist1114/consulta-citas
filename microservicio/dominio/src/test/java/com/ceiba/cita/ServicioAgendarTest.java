package com.ceiba.cita;

import com.ceiba.BasePrueba;
import com.ceiba.cita.modelo.entidad.Cita;
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

public class ServicioAgendarTest {

    @Test
    void debeeriaGenerarUnIdDeCitaYGuardar(){

        Paciente paciente = new PacienteTestDataBuilder()
                .conPacientePorDefecto().reconstruir();

        var solicitudAgendar = new SolicitudAgendarTestDataBuilder()
                .conPaciente(paciente)
                .conTipoProcedimiento(TipoProcedimiento.LIMPIEZA.toString())
                .build();

        var repositorioCita = Mockito.mock(RepositorioCita.class);
        var repositorioHistoria = Mockito.mock(RepositorioHistoria.class);

        Mockito.when(repositorioCita.guardar(Mockito.any())).thenReturn(1l);

        var servicioAgendar = new ServicioAgendar(repositorioCita, repositorioHistoria);

        var idCitaAgendada = servicioAgendar.ejecutar(solicitudAgendar);

        ArgumentCaptor<Cita> captorCita = ArgumentCaptor.forClass(Cita.class);
        Mockito.verify(repositorioCita, Mockito.times(1)).guardar(captorCita.capture());
        Assertions.assertEquals(1l, idCitaAgendada);
    }


}
