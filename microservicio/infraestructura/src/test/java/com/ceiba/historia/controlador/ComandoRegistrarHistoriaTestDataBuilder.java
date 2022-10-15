package com.ceiba.historia.controlador;

import com.ceiba.factura.comando.ComandoSolicitudRegistrarHistoria;
import com.ceiba.historia.modelo.entidad.Ubicaciones;

import java.time.LocalDate;


public class ComandoRegistrarHistoriaTestDataBuilder {

    private Long idPaciente;
    private LocalDate fechaEmision;
    private Ubicaciones ubicacion;

    public ComandoRegistrarHistoriaTestDataBuilder() {
    }

    public ComandoRegistrarHistoriaTestDataBuilder conIdPaciente(Long idPaciente) {
        this.idPaciente = idPaciente;
        return this;
    }

    public ComandoRegistrarHistoriaTestDataBuilder conFechaEmision(LocalDate fechaEmision) {
        this.fechaEmision = fechaEmision;
        return this;
    }

    public ComandoRegistrarHistoriaTestDataBuilder conUbicacion(String ubicacion) {
        this.ubicacion = Ubicaciones.valueOf(ubicacion);
        return this;
    }

    public ComandoSolicitudRegistrarHistoria build() {
        return new ComandoSolicitudRegistrarHistoria(idPaciente, fechaEmision, ubicacion);
    }
}
