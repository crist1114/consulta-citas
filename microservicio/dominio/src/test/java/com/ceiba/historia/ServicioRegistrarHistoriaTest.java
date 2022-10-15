package com.ceiba.historia;

import com.ceiba.historia.modelo.entidad.Historia;
import com.ceiba.historia.modelo.entidad.Ubicaciones;
import com.ceiba.historia.puerto.RepositorioHistoria;
import com.ceiba.historia.servicio.ServicioRegistrarHistoria;
import com.ceiba.paciente.PacienteTestDataBuilder;
import com.ceiba.paciente.modelo.entidad.Paciente;
import com.ceiba.paciente.puerto.repositorio.RepositorioPaciente;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.time.LocalDate;

public class ServicioRegistrarHistoriaTest {

    private RepositorioHistoria repositorioHistoria;

    @Test
    public void deberiaGuardarHistoria(){

        Paciente paciente = new PacienteTestDataBuilder()
                .conPacientePorDefecto().reconstruir();

        var solicitudRegistrarHistoria = new SolicitudRegistrarHistoriaTestDataBuilder()
                .conPaciente(paciente)
                .confechaEmision(LocalDate.of(2022,10,11))
                .conUbicacion(Ubicaciones.CASILLERO_4)
                .build();

        repositorioHistoria = Mockito.mock(RepositorioHistoria.class);
        Mockito.when(repositorioHistoria.guardar(Mockito.any())).thenReturn(1l);

        var servivioRegistrarHistoria = new ServicioRegistrarHistoria(repositorioHistoria);
        var idHistoria = servivioRegistrarHistoria.ejecutar(solicitudRegistrarHistoria);

        ArgumentCaptor<Historia> captorHistoria = ArgumentCaptor.forClass(Historia.class);
        Mockito.verify(repositorioHistoria, Mockito.times(1)).guardar(captorHistoria.capture());
        Assertions.assertEquals(paciente.getId(), captorHistoria.getValue().getIdPaciente());
        Assertions.assertEquals(LocalDate.of(2022,10,11),captorHistoria.getValue().getFechaEmision());
        Assertions.assertEquals(Ubicaciones.CASILLERO_4, captorHistoria.getValue().getUbicacion());
        Assertions.assertEquals(1l, idHistoria);
    }
}
