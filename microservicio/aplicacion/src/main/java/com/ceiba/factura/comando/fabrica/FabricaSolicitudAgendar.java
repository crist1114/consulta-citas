package com.ceiba.factura.comando.fabrica;

import com.ceiba.cita.modelo.entidad.SolicitudAgendar;
import com.ceiba.factura.comando.ComandoSolicitudAgendar;
import com.ceiba.paciente.puerto.repositorio.RepositorioPaciente;
import org.springframework.stereotype.Component;
import org.w3c.dom.ls.LSOutput;

import java.math.BigDecimal;
import java.sql.SQLOutput;

@Component
public class FabricaSolicitudAgendar {

    private RepositorioPaciente repositorioPaciente;

    public FabricaSolicitudAgendar(RepositorioPaciente repositorioPaciente) {
        this.repositorioPaciente = repositorioPaciente;
    }

    public SolicitudAgendar crear(ComandoSolicitudAgendar comandoSolicitudAgendar){

        return new SolicitudAgendar(
                    repositorioPaciente.obtener(comandoSolicitudAgendar.getIdPaciente()),
                    comandoSolicitudAgendar.getTipoProcedimiento(),
                    comandoSolicitudAgendar.getFecha(),
                    comandoSolicitudAgendar.getHora(),
                    new BigDecimal(comandoSolicitudAgendar.getValor())
                );
    }
}
