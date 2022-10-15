package com.ceiba.paciente;

import com.ceiba.paciente.modelo.entidad.SolicitudRegistrar;
import com.ceiba.paciente.modelo.entidad.TipoPaciente;

public class SolicitudRegistrarTestDataBuilder {

    private Long id;

    private String nombre;

    private String tipoPaciente;

    public SolicitudRegistrarTestDataBuilder conNombre(String nombre){
        this.nombre = nombre;
        return this;
    }

    public SolicitudRegistrarTestDataBuilder conTipoPaciente(TipoPaciente tipoPaciente){
        this.tipoPaciente = tipoPaciente.toString();
        return this;
    }

    public SolicitudRegistrarTestDataBuilder conId(Long id){
        this.id = id;
        return this;
    }

    public SolicitudRegistrar build() {
        return new SolicitudRegistrar(id, nombre, tipoPaciente);
    }
}
