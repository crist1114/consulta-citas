package com.ceiba.factura.consulta;

import com.ceiba.paciente.entidad.ResumenPacienteDTO;
import com.ceiba.paciente.puerto.dao.DaoPaciente;
import org.springframework.stereotype.Component;

@Component
public class ManejadorConsultarPacientes {

    private final DaoPaciente daoPaciente;

    public ManejadorConsultarPacientes(DaoPaciente daoPaciente) {
        this.daoPaciente = daoPaciente;
    }

    public ResumenPacienteDTO ejecutar(Long id) {
        return daoPaciente.obtenerPaciente(id);
    }
}
