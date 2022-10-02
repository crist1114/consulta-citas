package com.ceiba.paciente;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import com.ceiba.paciente.entidad.TipoPaciente;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PacienteTest {

    @Test
    void deberiaCrearPacienteExitoso(){

        var paciente = new PacienteTestDataBuilder()
                .conTipoPaciente(TipoPaciente.CONTRIBUTIVO)
                .conId(1l)
                .conNombre("Paciente 1").reconstruir();

        Assertions.assertEquals("Paciente 1", paciente.getNombre());
        Assertions.assertEquals(TipoPaciente.CONTRIBUTIVO, paciente.getTipoPaciente());
        Assertions.assertEquals(1l, paciente.getId());
    }

    @Test
    void reconstruirPacienteSinTipoDeberiaLanzarError(){
        BasePrueba.assertThrows(()->new PacienteTestDataBuilder()
                        .conNombre("Paciente 1")
                        .conId(1l)
                        .reconstruir(),
                        ExcepcionValorObligatorio.class,
                "El tipo paciente es requerido");
    }

    @Test
    void reconstruirPacienteSinNombreDeberiaLanzarError(){
        BasePrueba.assertThrows(()->new PacienteTestDataBuilder()
                        .conTipoPaciente(TipoPaciente.CONTRIBUTIVO)
                        .conId(1l)
                        .reconstruir(),
                ExcepcionValorObligatorio.class,
                "El nombre es requerido");
    }

    @Test
    void reconstruirPacienteSinIdDeberiaLanzarError(){
        BasePrueba.assertThrows(()-> new PacienteTestDataBuilder()
                .conTipoPaciente(TipoPaciente.CONTRIBUTIVO)
                .conNombre("Paciente 1")
                .reconstruir(),
                ExcepcionValorObligatorio.class,
                "El id es requerido"
        );
    }

    @Test
    void deberiaREsponderEsPacienteContributivoCorrectamente(){
        var paciente = new PacienteTestDataBuilder()
                .conTipoPaciente(TipoPaciente.CONTRIBUTIVO)
                .conNombre("Paciente 1")
                .conId(1l)
                .reconstruir();

        Assertions.assertTrue(paciente.esContributivo());
        Assertions.assertFalse(paciente.esSubsidiado());
    }

    @Test
    void deberiaREsponderEsPacienteSubsidiadoCorrectamente(){
        var paciente = new PacienteTestDataBuilder()
                .conTipoPaciente(TipoPaciente.SUBSIDIADO)
                .conNombre("Paciente 1")
                .conId(1l)
                .reconstruir();

        Assertions.assertTrue(paciente.esSubsidiado());
        Assertions.assertFalse(paciente.esContributivo());
    }
}
