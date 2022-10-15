package com.ceiba.factura.consulta;

import com.ceiba.cita.modelo.dto.ResumenCitaDTO;
import com.ceiba.cita.puerto.dao.DaoCita;
import org.springframework.stereotype.Component;

@Component
public class ManejadorConsultarCita {

    private final DaoCita daoCita;

    public ManejadorConsultarCita(DaoCita daoCita) {
        this.daoCita = daoCita;
    }

    public ResumenCitaDTO ejecutar(Long idPaciente){

        return daoCita.obtenerCitaNoAtendidaPaciente(idPaciente);
    }
}
