package com.ceiba.historia;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import com.ceiba.historia.modelo.entidad.Historia;
import com.ceiba.historia.modelo.entidad.Ubicaciones;
import com.ceiba.paciente.PacienteTestDataBuilder;
import com.ceiba.paciente.modelo.entidad.Paciente;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class HistoriaTest {

    @Test
    public void deberiaGuardarHistoriaCorrectamenteCasillero1(){

        Paciente paciente = new PacienteTestDataBuilder()
                .conPacientePorDefecto().reconstruir();

        Historia historia = new HistoriaTestDataBuilder()
                .conPaciente(paciente)
                .confechaEmision(LocalDate.of(2022,10,11))
                .conUbicacion("CASILLERO_1")
                .crear();

        Assertions.assertEquals(1091l, historia.getIdPaciente());
        Assertions.assertEquals("2022-10-11", historia.getFechaEmision().toString());
        Assertions.assertEquals(Ubicaciones.CASILLERO_1, historia.getUbicacion());

    }

    @Test
    public void deberiaGuardarHistoriaCorrectamenteCasillero2(){

        Paciente paciente = new PacienteTestDataBuilder()
                .conPacientePorDefecto().reconstruir();

        Historia historia = new HistoriaTestDataBuilder()
                .conPaciente(paciente)
                .confechaEmision(LocalDate.of(2022,10,11))
                .conUbicacion("CASILLERO_2")
                .crear();

        Assertions.assertEquals(1091l, historia.getIdPaciente());
        Assertions.assertEquals("2022-10-11", historia.getFechaEmision().toString());
        Assertions.assertEquals(Ubicaciones.CASILLERO_2, historia.getUbicacion());

    }
    @Test
    public void deberiaGuardarHistoriaCorrectamenteCasillero3(){

        Paciente paciente = new PacienteTestDataBuilder()
                .conPacientePorDefecto().reconstruir();

        Historia historia = new HistoriaTestDataBuilder()
                .conPaciente(paciente)
                .confechaEmision(LocalDate.of(2022,10,11))
                .conUbicacion("CASILLERO_3")
                .crear();

        Assertions.assertEquals(1091l, historia.getIdPaciente());
        Assertions.assertEquals("2022-10-11", historia.getFechaEmision().toString());
        Assertions.assertEquals(Ubicaciones.CASILLERO_3, historia.getUbicacion());

    }

    @Test
    public void deberiaGuardarHistoriaCorrectamenteCasillero4(){

        Paciente paciente = new PacienteTestDataBuilder()
                .conPacientePorDefecto().reconstruir();

        Historia historia = new HistoriaTestDataBuilder()
                .conPaciente(paciente)
                .confechaEmision(LocalDate.of(2022,10,11))
                .conUbicacion("CASILLERO_4")
                .crear();

        Assertions.assertEquals(1091l, historia.getIdPaciente());
        Assertions.assertEquals("2022-10-11", historia.getFechaEmision().toString());
        Assertions.assertEquals(Ubicaciones.CASILLERO_4, historia.getUbicacion());

    }

    @Test
    void DeberiaLanzarExcepcionNoExistePaciente() {

        BasePrueba.assertThrows(()->new HistoriaTestDataBuilder()
                        .confechaEmision(LocalDate.of(2022,10,11))
                        .conUbicacion("CASILLERO_4")
                        .crear(),
                ExcepcionValorObligatorio.class,
                "Debe ingresar un id de paciente");
    }

    @Test
    void DeberiaLanzarExcepcionIngresarFechaEmision() {
        Paciente paciente = new PacienteTestDataBuilder()
                .conPacientePorDefecto().reconstruir();

        BasePrueba.assertThrows(()->new HistoriaTestDataBuilder()
                        .conPaciente(paciente)
                        .conUbicacion("CASILLERO_4")
                        .crear(),
                ExcepcionValorObligatorio.class,
                "Debe ingresar una fecha de emision");
    }

    @Test
    void DeberiaLanzarExcepcionIngresarUnaUbicacion() {
        Paciente paciente = new PacienteTestDataBuilder()
                .conPacientePorDefecto().reconstruir();

        BasePrueba.assertThrows(()->new HistoriaTestDataBuilder()
                        .confechaEmision(LocalDate.of(2022,10,11))
                        .conPaciente(paciente)
                        .crear(),
                ExcepcionValorObligatorio.class,
                "Debe ingresar una ubicación");
    }
}
