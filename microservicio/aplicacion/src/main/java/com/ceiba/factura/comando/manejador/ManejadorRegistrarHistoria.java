package com.ceiba.factura.comando.manejador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.factura.comando.ComandoSolicitudRegistrarHistoria;
import com.ceiba.factura.comando.fabrica.historia.FabricaSolicitudRegistrarHistoria;
import com.ceiba.historia.servicio.ServicioRegistrarHistoria;
import com.ceiba.manejador.ManejadorComandoRespuesta;
import org.springframework.stereotype.Component;


@Component
public class ManejadorRegistrarHistoria implements ManejadorComandoRespuesta<ComandoSolicitudRegistrarHistoria, ComandoRespuesta<Long>> {

    private FabricaSolicitudRegistrarHistoria fabricaSolicitudRegistrarHistoria;
    private ServicioRegistrarHistoria servicioRegistrarHistoria;

    public ManejadorRegistrarHistoria(FabricaSolicitudRegistrarHistoria fabricaSolicitudRegistrarHistoria, ServicioRegistrarHistoria servicioRegistrarHistoria) {
        this.fabricaSolicitudRegistrarHistoria = fabricaSolicitudRegistrarHistoria;
        this.servicioRegistrarHistoria = servicioRegistrarHistoria;
    }

    @Override
    public ComandoRespuesta<Long> ejecutar(ComandoSolicitudRegistrarHistoria comando) {

        return new ComandoRespuesta<>(
                servicioRegistrarHistoria.ejecutar(fabricaSolicitudRegistrarHistoria.crear(comando))
                );
    }
}
