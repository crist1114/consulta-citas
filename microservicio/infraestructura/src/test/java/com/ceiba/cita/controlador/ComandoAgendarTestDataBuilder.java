package com.ceiba.cita.controlador;
import com.ceiba.cita.modelo.entidad.TipoProcedimiento;
import com.ceiba.factura.comando.ComandoSolicitudAgendar;

import java.time.LocalDate;
import java.time.LocalTime;

public class ComandoAgendarTestDataBuilder {

    private Long id_paciente;
    private String tipo_procedimiento;
    private LocalDate fecha;
    private LocalTime hora;
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

    public ComandoAgendarTestDataBuilder conFecha(LocalDate fecha) {
        this.fecha = fecha;
        return this;
    }
    public ComandoAgendarTestDataBuilder conHora(LocalTime hora) {
        this.hora = hora;
        return this;
    }

    public ComandoSolicitudAgendar build() {
        return new ComandoSolicitudAgendar(this.id_paciente, this.tipo_procedimiento,this.fecha,this.hora, this.valor);
    }

}
