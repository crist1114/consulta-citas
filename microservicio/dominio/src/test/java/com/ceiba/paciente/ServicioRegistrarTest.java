package com.ceiba.paciente;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
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

        //preparar
        Paciente paciente = new PacienteTestDataBuilder()
                .conPacientePorDefecto()
                .reconstruir();

        var solicitudRegistrar = new SolicitudRegistrarTestDataBuilder()
                .conId(1091l)
                .conTipoPaciente(TipoPaciente.CONTRIBUTIVO)
                .conNombre("Paciente 1")
                .build();

        var repositorioPaciente = Mockito.mock(RepositorioPaciente.class);
        var servicioRegistrar = new ServicioRegistrar(repositorioPaciente);

        //actuar
        Mockito.when(repositorioPaciente.guardar(Mockito.any())).thenReturn(1091l);
        var idPacienteRegistrado = servicioRegistrar.ejecutar(solicitudRegistrar);
        ArgumentCaptor<Paciente> captorPaciente = ArgumentCaptor.forClass(Paciente.class);
        Mockito.verify(repositorioPaciente, Mockito.times(1)).guardar(captorPaciente.capture());

        //verificar
        Assertions.assertEquals(paciente.getTipoPaciente(), captorPaciente.getValue().getTipoPaciente());
        Assertions.assertEquals(1091l, idPacienteRegistrado);
   }

    @Test
    void deberiaLanzarExcepcionPacienteYaExiste(){

        //preparar
        Paciente paciente = new PacienteTestDataBuilder()
                .conPacientePorDefecto()
                .reconstruir();

        var solicitudRegistrar = new SolicitudRegistrarTestDataBuilder()
                .conId(paciente.getId())
                .conTipoPaciente(paciente.getTipoPaciente())
                .conNombre(paciente.getNombre())
                .build();

        var repositorioPaciente = Mockito.mock(RepositorioPaciente.class);

        //actuar
        Mockito.when(repositorioPaciente.guardar(Mockito.any())).thenReturn(1091l);
        Mockito.when(repositorioPaciente.obtener(Mockito.any())).thenReturn(paciente);

        var servicioRegistrar = new ServicioRegistrar(repositorioPaciente);

        //verificar
        BasePrueba.assertThrows(()->
                        servicioRegistrar.ejecutar(solicitudRegistrar),
                ExcepcionDuplicidad.class,
                "El paciente ya se encuentra registrado"
        );
    }
}
