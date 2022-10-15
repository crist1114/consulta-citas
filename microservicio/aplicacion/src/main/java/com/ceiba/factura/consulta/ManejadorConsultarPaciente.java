package com.ceiba.factura.consulta;

import com.ceiba.paciente.modelo.dto.ResumenPacienteDTO;
import com.ceiba.paciente.puerto.dao.DaoPaciente;
import org.springframework.stereotype.Component;

@Component
public class ManejadorConsultarPaciente {


    private final DaoPaciente daoPaciente;


    public ManejadorConsultarPaciente(DaoPaciente daoPaciente) {
        this.daoPaciente = daoPaciente;
    }

    public ResumenPacienteDTO ejecutar(Long id){

        return daoPaciente.obtenerPaciente(id);
    }
}
