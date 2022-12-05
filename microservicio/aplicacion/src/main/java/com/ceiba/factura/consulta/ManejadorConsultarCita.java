package com.ceiba.factura.consulta;

import com.ceiba.cita.modelo.dto.ResumenCitaDTO;
import com.ceiba.cita.puerto.dao.DaoCita;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class ManejadorConsultarCita {

    private final DaoCita daoCita;

    public ManejadorConsultarCita(DaoCita daoCita) {
        this.daoCita = daoCita;
    }

    public ResumenCitaDTO ejecutar(Long idPaciente){
        return daoCita.obtenerCitaNoAtendidaPaciente(idPaciente);
    }

    public List<ResumenCitaDTO> ejecutarObtener(){
        return daoCita.obtenerCitas();
    }

    public List<ResumenCitaDTO> ejecutarObtenerCitasPorFecha(String fecha) {
        return daoCita.obtenerCitasPorFecha(fecha);
    }
}
