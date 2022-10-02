package com.ceiba.paciente.servicio;

import com.ceiba.cita.modelo.entidad.Cita;
import com.ceiba.cita.modelo.entidad.SolicitudAgendar;
import com.ceiba.dominio.ValidadorArgumento;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.paciente.entidad.Paciente;
import com.ceiba.paciente.entidad.SolicitudRegistrar;
import com.ceiba.paciente.puerto.RepositorioPaciente;

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
