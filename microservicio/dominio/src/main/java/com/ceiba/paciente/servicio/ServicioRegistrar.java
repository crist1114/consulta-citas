package com.ceiba.paciente.servicio;

import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.paciente.modelo.entidad.Paciente;
import com.ceiba.paciente.modelo.entidad.SolicitudRegistrar;
import com.ceiba.paciente.puerto.repositorio.RepositorioPaciente;

public class ServicioRegistrar {

    private final RepositorioPaciente repositorioPaciente;

    public ServicioRegistrar(RepositorioPaciente repositorioPaciente) {
        this.repositorioPaciente = repositorioPaciente;
    }

    public Long ejecutar(SolicitudRegistrar solicitudRegistrar) {

        if(repositorioPaciente.obtener(solicitudRegistrar.getId())!=null)
            throw new ExcepcionDuplicidad("El paciente ya se encuentra registrado");

        var paciente = Paciente.crear(solicitudRegistrar);

        return repositorioPaciente.guardar(paciente);
    }
}
