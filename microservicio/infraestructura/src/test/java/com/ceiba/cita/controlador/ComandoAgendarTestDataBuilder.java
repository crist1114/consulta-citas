package com.ceiba.cita.controlador;

import com.ceiba.cita.modelo.entidad.TipoProcedimiento;
import com.ceiba.factura.comando.ComandoSolicitudAgendar;

public class ComandoAgendarTestDataBuilder {

    private Long id_paciente;

    private String tipo_procedimiento;

    public ComandoAgendarTestDataBuilder() {

    }

    public ComandoAgendarTestDataBuilder crearPorDefecto() {
        this.id_paciente = 1l;
        this.tipo_procedimiento = TipoProcedimiento.LIMPIEZA.toString();
        return this;

    }

    public ComandoSolicitudAgendar build() {
        return new ComandoSolicitudAgendar(this.id_paciente, this.tipo_procedimiento);
    }
}
