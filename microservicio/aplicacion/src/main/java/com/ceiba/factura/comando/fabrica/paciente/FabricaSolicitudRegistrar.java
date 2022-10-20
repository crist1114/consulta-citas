package com.ceiba.factura.comando.fabrica.paciente;

import com.ceiba.factura.comando.ComandoSolicitudRegistrar;
import com.ceiba.paciente.modelo.entidad.SolicitudRegistrar;
import org.springframework.stereotype.Component;

@Component
public class FabricaSolicitudRegistrar {

    public SolicitudRegistrar crear(ComandoSolicitudRegistrar comandoSolicitudRegistrar) {
        return new SolicitudRegistrar(comandoSolicitudRegistrar.getId(),
                comandoSolicitudRegistrar.getNombre(),
                comandoSolicitudRegistrar.getTipoPaciente()
        );
    }
}
