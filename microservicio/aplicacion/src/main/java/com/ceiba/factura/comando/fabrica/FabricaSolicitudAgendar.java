package com.ceiba.factura.comando.fabrica;

import com.ceiba.cita.modelo.entidad.SolicitudAgendar;
import com.ceiba.factura.comando.ComandoSolicitudAgendar;
import org.springframework.stereotype.Component;

import java.sql.SQLOutput;

@Component
public class FabricaSolicitudAgendar {



    public FabricaSolicitudAgendar() {

    }

    public SolicitudAgendar crear(ComandoSolicitudAgendar comandoSolicitudAgendar){


        return new SolicitudAgendar(
                    comandoSolicitudAgendar.getId_paciente(),
                    comandoSolicitudAgendar.getTipo_procedimiento()
                );
    }
}
