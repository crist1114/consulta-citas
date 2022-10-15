package com.ceiba.cita;

import com.ceiba.BasePrueba;
import com.ceiba.cita.modelo.entidad.EstadoCita;
import com.ceiba.cita.modelo.entidad.TipoProcedimiento;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import com.ceiba.paciente.PacienteTestDataBuilder;
import com.ceiba.paciente.modelo.entidad.Paciente;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

public class CitaTest {

    @Test
    void deberiaCrearLaCitaCorrectamenteConTipoProcedimientoCalzaDeMuela() {

        Paciente paciente = new PacienteTestDataBuilder()
                .conPacientePorDefecto().reconstruir();

        var cita = new CitaTestDataBuilder()
                .conPaciente(paciente)
                        .conTipoProcedimiento(TipoProcedimiento.CALZA_DE_MUELA)
                            .conFecha(LocalDate.now().plusDays(2))
                            .conHora(LocalTime.of(15, 00,00))
                            .conValorPagado(55000)
                                .crear();

        Assertions.assertEquals(1091L, cita.getIdPaciente());
        Assertions.assertEquals(TipoProcedimiento.CALZA_DE_MUELA.toString(), cita.getTipoProcedimiento().toString());

    }

    @Test
    void deberiaCrearLaCitaCorrectamenteConTipoProcedimientoLimpieza() {

        Paciente paciente = new PacienteTestDataBuilder()
                .conPacientePorDefecto().reconstruir();

        var cita = new CitaTestDataBuilder()
                .conPaciente(paciente)
                .conTipoProcedimiento(TipoProcedimiento.LIMPIEZA)
                .conFecha(LocalDate.now().plusDays(2))
                .conHora(LocalTime.of(15, 00,00))
                .conValorPagado(55000)
                .crear();

        Assertions.assertEquals(1091L, cita.getIdPaciente());
        Assertions.assertEquals(TipoProcedimiento.LIMPIEZA.toString(), cita.getTipoProcedimiento().toString());

    }

    @Test
    void deberiaCrearLaCitaCorrectamenteConTipoProcedimientoBlanqueamientoDental() {

        Paciente paciente = new PacienteTestDataBuilder()
                .conPacientePorDefecto().reconstruir();

        var cita = new CitaTestDataBuilder()
                .conPaciente(paciente)
                .conTipoProcedimiento(TipoProcedimiento.BLANQUEAMIENTO_DENTAL)
                .conFecha(LocalDate.now().plusDays(2))
                .conHora(LocalTime.of(15, 00,00))
                .conValorPagado(55000)
                .crear();

        Assertions.assertEquals(1091L, cita.getIdPaciente());
        Assertions.assertEquals(TipoProcedimiento.BLANQUEAMIENTO_DENTAL.toString(), cita.getTipoProcedimiento().toString());

    }

    @Test
    void deberiaCrearLaCitaCorrectamenteConEstadoNoAtendidaPorDefecto() {

        Paciente paciente = new PacienteTestDataBuilder()
                .conPacientePorDefecto().reconstruir();

        var cita = new CitaTestDataBuilder()
                .conPaciente(paciente)
                .conTipoProcedimiento(TipoProcedimiento.CALZA_DE_MUELA)
                .conFecha(LocalDate.now().plusDays(2))
                .conHora(LocalTime.of(15, 00,00))
                .conValorPagado(55000)
                .crear();

        Assertions.assertEquals(1091L, cita.getIdPaciente());
        Assertions.assertEquals(TipoProcedimiento.CALZA_DE_MUELA.toString(), cita.getTipoProcedimiento().toString());
        Assertions.assertEquals(EstadoCita.NO_ATENDIDA, cita.getEstado());
    }

    @Test
    void DeberiaLanzarExcepcionNoExistePaciente() {

        BasePrueba.assertThrows(()->new CitaTestDataBuilder()
                        .conTipoProcedimiento(TipoProcedimiento.LIMPIEZA)
                        .conFecha(LocalDate.now().plusDays(2))
                        .conHora(LocalTime.of(15, 00,00))
                        .conValorPagado(55000)
                        .crear(),
                ExcepcionValorObligatorio.class,
                "El paciente no existe");
    }
    @Test
    void deberiaLanzarExcepcionDebeIngresarUnMonto() {
        Paciente paciente = new PacienteTestDataBuilder()
                .conPacientePorDefecto().reconstruir();

        BasePrueba.assertThrows(()->new CitaTestDataBuilder()
                        .conPaciente(paciente)
                        .conTipoProcedimiento(TipoProcedimiento.LIMPIEZA)
                        .crear(),
                ExcepcionValorObligatorio.class,
                "Debe ingresar el monto de la cita");
    }


}
