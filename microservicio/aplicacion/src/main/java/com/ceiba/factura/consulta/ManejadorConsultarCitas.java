package com.ceiba.factura.consulta;

import com.ceiba.cita.modelo.dto.ResumenCitaDTO;
import com.ceiba.cita.puerto.dao.DaoCita;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ManejadorConsultarCitas {

    private final DaoCita daoCita;

    public ManejadorConsultarCitas(DaoCita daoCita) {
        this.daoCita = daoCita;
    }

    public List<ResumenCitaDTO> ejecutar(Long idPaciente){
        return daoCita.obtenerCitasPaciente(idPaciente);
    }


}
