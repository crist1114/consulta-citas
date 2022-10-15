package com.ceiba.factura.comando.fabrica;

import com.ceiba.factura.comando.ComandoSolicitudRegistrarHistoria;
import com.ceiba.historia.modelo.entidad.SolicitudRegistrarHistoria;
import com.ceiba.paciente.puerto.repositorio.RepositorioPaciente;
import org.springframework.stereotype.Component;

@Component
public class FabricaSolicitudRegistrarHistoria {

    private RepositorioPaciente repositorioPaciente;

    public FabricaSolicitudRegistrarHistoria(RepositorioPaciente repositorioPaciente) {
        this.repositorioPaciente = repositorioPaciente;
    }

    public SolicitudRegistrarHistoria crear(ComandoSolicitudRegistrarHistoria comandoSolicitud){

        return new SolicitudRegistrarHistoria(
                repositorioPaciente.obtener(comandoSolicitud.getIdPaciente()),
                comandoSolicitud.getFechaEmision(),
                comandoSolicitud.getUbicacion());
    }

}
