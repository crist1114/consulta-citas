package com.ceiba.cita;

import com.ceiba.BasePrueba;
import com.ceiba.cita.modelo.dto.ResumenCitaDTO;
import com.ceiba.cita.modelo.entidad.EstadoCita;
import com.ceiba.cita.modelo.entidad.TipoProcedimiento;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;

public class CitaTest {

    @Test
    void deberiaCrearLaCitaCorrectamenteConTipoProcedimientoCalzaDeMuela() {

        var cita = new CitaTestDataBuilder()
                .conIdPaciente(1090L)
                        .conTipoProcedimiento(TipoProcedimiento.CALZA_DE_MUELA)
                                .crear(LocalDate.parse("2022-02-02"),
                                        Arrays.asList(new ResumenCitaDTO(1l, EstadoCita.ATENDIDA)));

        Assertions.assertEquals(1090L, cita.getIdPaciente());
        Assertions.assertEquals(TipoProcedimiento.CALZA_DE_MUELA.toString(), cita.getTipoProcedimiento().toString());

    }

    @Test
    void deberiaCrearLaCitaCorrectamenteConTipoProcedimientoLimpieza() {

        var cita = new CitaTestDataBuilder()
                .conIdPaciente(1090L)
                .conTipoProcedimiento(TipoProcedimiento.LIMPIEZA)
                .crear(LocalDate.parse("2022-02-02"),
                        Arrays.asList(new ResumenCitaDTO(1l, EstadoCita.ATENDIDA)));

        Assertions.assertEquals(1090L, cita.getIdPaciente());
        Assertions.assertEquals(TipoProcedimiento.LIMPIEZA.toString(), cita.getTipoProcedimiento().toString());

    }

    @Test
    void deberiaCrearLaCitaCorrectamenteConTipoProcedimientoBlanqueamientoDental() {

        var cita = new CitaTestDataBuilder()
                .conIdPaciente(1090L)
                .conTipoProcedimiento(TipoProcedimiento.BLANQUEAMIENTO_DENTAL)
                .crear(LocalDate.parse("2022-02-02"),
                        Arrays.asList(new ResumenCitaDTO(1l, EstadoCita.ATENDIDA)));

        Assertions.assertEquals(1090L, cita.getIdPaciente());
        Assertions.assertEquals(TipoProcedimiento.BLANQUEAMIENTO_DENTAL.toString(), cita.getTipoProcedimiento().toString());

    }

    @Test
    void deberiaCrearLaCitaCorrectamenteConTipoProcedimientoMantenimientoDeBracketsSinHistoriasRegistradas() {

        var cita = new CitaTestDataBuilder()
                .conIdPaciente(1090L)
                .conTipoProcedimiento(TipoProcedimiento.MANTENIMIENTO_DE_BRACKETS)
                .crear(null,
                        Arrays.asList(new ResumenCitaDTO(1l, EstadoCita.ATENDIDA)));

        Assertions.assertEquals(1090L, cita.getIdPaciente());
        Assertions.assertEquals(TipoProcedimiento.MANTENIMIENTO_DE_BRACKETS.toString(), cita.getTipoProcedimiento().toString());

    }

    @Test
    void deberiaCrearLaCitaCorrectamenteConEstadoNoAtendidaPorDefecto() {

        var cita = new CitaTestDataBuilder()
                .conIdPaciente(1090L)
                .conTipoProcedimiento(TipoProcedimiento.CALZA_DE_MUELA)
                .conEstado()
                .crear(LocalDate.parse("2022-02-02"),
                        Arrays.asList(new ResumenCitaDTO(1l, EstadoCita.ATENDIDA)));

        Assertions.assertEquals(1090L, cita.getIdPaciente());
        Assertions.assertEquals(TipoProcedimiento.CALZA_DE_MUELA.toString(), cita.getTipoProcedimiento().toString());
        Assertions.assertEquals(EstadoCita.NO_ATENDIDA, cita.getEstado());
    }

    @Test
    void DeberiaLanzarExcepcionPorHistoriaVencidaMantenimientoBrackets() {

        BasePrueba.assertThrows(()->new CitaTestDataBuilder()
                        .conIdPaciente(1090l)
                        .conTipoProcedimiento(TipoProcedimiento.MANTENIMIENTO_DE_BRACKETS)
                        .crear(LocalDate.parse("2022-02-02"),
                                Arrays.asList(new ResumenCitaDTO(1l, EstadoCita.ATENDIDA))),
                ExcepcionValorInvalido.class,
                "Debe agendar cita para limpieza ya que su ultima historia registrada es mayor a 3 meses");
    }

    @Test
    void DeberiaLanzarExcepcionPorqueYaTieneCitaEstadoNoAtendida() {

        BasePrueba.assertThrows(()->new CitaTestDataBuilder()
                        .conIdPaciente(1090l)
                        .conTipoProcedimiento(TipoProcedimiento.LIMPIEZA)
                        .crear(LocalDate.parse("2022-02-02"),
                                Arrays.asList(new ResumenCitaDTO(1l, EstadoCita.NO_ATENDIDA))),
                ExcepcionDuplicidad.class,
                "El paciente ya tiene una cita agendada");
    }

    @Test
    void DeberiaLanzarExcepcionNoInsertoPaciente() {

        BasePrueba.assertThrows(()->new CitaTestDataBuilder()
                        .conTipoProcedimiento(TipoProcedimiento.LIMPIEZA)
                        .crear(LocalDate.parse("2022-02-02"),
                                Arrays.asList(new ResumenCitaDTO(1l, EstadoCita.ATENDIDA))),
                ExcepcionValorObligatorio.class,
                "El paciente es requerido para agendar");
    }
}
