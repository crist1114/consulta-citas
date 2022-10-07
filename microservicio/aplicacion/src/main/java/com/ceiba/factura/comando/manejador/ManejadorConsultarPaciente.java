package com.ceiba.factura.comando.manejador;

import com.ceiba.paciente.entidad.ResumenPacienteDTO;
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
