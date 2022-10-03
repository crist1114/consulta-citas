package com.ceiba.factura.comando.manejador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.factura.comando.ComandoSolicitudRegistrar;
import com.ceiba.factura.comando.fabrica.FabricaSolicitudRegistrar;
import com.ceiba.manejador.ManejadorComandoRespuesta;
import com.ceiba.paciente.servicio.ServicioRegistrar;
import org.springframework.stereotype.Component;

@Component
public class ManejadorRegistrar implements ManejadorComandoRespuesta<ComandoSolicitudRegistrar, ComandoRespuesta<Long>> {

   private ServicioRegistrar servicioRegistrar;

   private FabricaSolicitudRegistrar fabricaSolicitudRegistrar;

    public ManejadorRegistrar(ServicioRegistrar servicioRegistrar, FabricaSolicitudRegistrar fabricaSolicitudRegistrar) {
        this.servicioRegistrar = servicioRegistrar;
        this.fabricaSolicitudRegistrar = fabricaSolicitudRegistrar;
    }

    @Override
    public ComandoRespuesta<Long> ejecutar(ComandoSolicitudRegistrar comando) {
        return new ComandoRespuesta<>(
                servicioRegistrar.ejecutar(
                        fabricaSolicitudRegistrar.crear(comando)
                ));
    }
}
