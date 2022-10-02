package com.ceiba.factura.comando.manejador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.cita.servicio.ServicioAgendar;
import com.ceiba.factura.comando.ComandoSolicitudAgendar;
import com.ceiba.factura.comando.fabrica.FabricaSolicitudAgendar;
import com.ceiba.manejador.ManejadorComandoRespuesta;
import org.springframework.stereotype.Component;

@Component
public class ManejadorAgendar implements ManejadorComandoRespuesta<ComandoSolicitudAgendar, ComandoRespuesta<Long>> {

    private ServicioAgendar servicioAgendar;
    private FabricaSolicitudAgendar fabricaSolicitudAgendar;

    public ManejadorAgendar(ServicioAgendar servicioAgendar, FabricaSolicitudAgendar fabricaSolicitudAgendar) {
        this.servicioAgendar = servicioAgendar;
        this.fabricaSolicitudAgendar = fabricaSolicitudAgendar;
    }

    @Override
    public ComandoRespuesta<Long> ejecutar(ComandoSolicitudAgendar comandoSolicitudAgendar) {

        return new ComandoRespuesta<>(
                servicioAgendar.ejecutar(
                        fabricaSolicitudAgendar.crear(comandoSolicitudAgendar)
        ));
    }

}
