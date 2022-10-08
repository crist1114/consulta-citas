package com.ceiba.paciente.controlador;

import com.ceiba.factura.comando.manejador.ManejadorConsultarPaciente;
import com.ceiba.paciente.dto.ResumenPacienteDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/paciente")
@Tag(name = "Controlador consulta paciente")
public class ConsultaConstroladorPaciente {

    private final ManejadorConsultarPaciente manejadorConsultarPacientes;

    public ConsultaConstroladorPaciente(ManejadorConsultarPaciente manejadorConsultarPacientes) {
        this.manejadorConsultarPacientes = manejadorConsultarPacientes;
    }

    @GetMapping("/{id}")
    @Operation(summary = "consultar", description = "Metodo utilizado para consultar un paciente")
    public ResumenPacienteDTO obtenerPaciente(@PathVariable Long id) {

        return manejadorConsultarPacientes.ejecutar(id);
    }

}
