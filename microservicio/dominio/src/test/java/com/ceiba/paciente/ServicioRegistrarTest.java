package com.ceiba.paciente;

import com.ceiba.paciente.entidad.Paciente;
import com.ceiba.paciente.entidad.TipoPaciente;
import com.ceiba.paciente.puerto.repositorio.RepositorioPaciente;
import com.ceiba.paciente.servicio.ServicioRegistrar;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;


public class ServicioRegistrarTest {

    @Test
    void deberiaGenerarIdDeRegistro(){
        Paciente paciente = new PacienteTestDataBuilder()
                .conPacientePorDefecto()
                .reconstruir();

        var solicitudRegistrar = new SolicitudRegistrarTestDataBuilder()
                .conId(1091l)
                .conTipoPaciente(TipoPaciente.CONTRIBUTIVO)
                .conNombre("Paciente 1")
                .build();

        var repositorioPaciente = Mockito.mock(RepositorioPaciente.class);

        Mockito.when(repositorioPaciente.guardar(Mockito.any())).thenReturn(1091l);
        var servicioRegistrar = new ServicioRegistrar(repositorioPaciente);

       var idPacienteRegistrado = servicioRegistrar.ejecutar(solicitudRegistrar);

       ArgumentCaptor<Paciente> captorPaciente = ArgumentCaptor.forClass(Paciente.class);
       Mockito.verify(repositorioPaciente, Mockito.times(1)).guardar(captorPaciente.capture());
       Assertions.assertEquals(paciente.getTipoPaciente(), captorPaciente.getValue().getTipoPaciente());
       Assertions.assertEquals(1091l, idPacienteRegistrado);
   }
}
