package com.ceiba.cita.controlador;

import com.ceiba.cita.modelo.entidad.EstadoCita;
import com.ceiba.cita.modelo.entidad.TipoProcedimiento;
import com.ceiba.factura.comando.ComandoSolicitudAgendar;

import java.math.BigDecimal;

public class ComandoAgendarTestDataBuilder {

    private Long id_paciente;
    private String tipo_procedimiento;

    private double valor;


    public ComandoAgendarTestDataBuilder() {

    }

    public ComandoAgendarTestDataBuilder crearPorDefecto() {
        this.id_paciente = 1092l;
        this.tipo_procedimiento = TipoProcedimiento.LIMPIEZA.toString();
        this.valor = 55000;
        return this;

    }

    public ComandoAgendarTestDataBuilder conIdPaciente(Long id) {
        this.id_paciente = id;
        return this;

    }

    public ComandoSolicitudAgendar build() {
        return new ComandoSolicitudAgendar(this.id_paciente, this.tipo_procedimiento, this.valor);
    }
}
