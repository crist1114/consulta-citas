package com.ceiba.historia;

import com.ceiba.historia.modelo.entidad.Historia;
import com.ceiba.historia.modelo.entidad.Ubicaciones;
import com.ceiba.paciente.modelo.entidad.Paciente;

import java.time.LocalDate;

public class HistoriaTestDataBuilder {

    private Paciente paciente;
    private LocalDate fechaEmision;
    private Ubicaciones ubicacion;


    public HistoriaTestDataBuilder conPaciente(Paciente paciente){
        this.paciente = paciente;
        return this;

    }

    public HistoriaTestDataBuilder confechaEmision(LocalDate fechaEmision){
        this.fechaEmision = fechaEmision;
        return this;
    }

    public HistoriaTestDataBuilder conUbicacion(String ubicacion){
        this.ubicacion = Ubicaciones.valueOf(ubicacion);
        return this;
    }

    public Historia crear(){
        return Historia.crear(new SolicitudRegistrarHistoriaTestDataBuilder()
                        .conPaciente(paciente)
                        .confechaEmision(fechaEmision)
                        .conUbicacion(ubicacion)
                .build());
    }


}
