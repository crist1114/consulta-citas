package com.ceiba.paciente.controlador;

import com.ceiba.factura.consulta.ManejadorConsultarPaciente;
import com.ceiba.paciente.modelo.dto.ResumenPacienteDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/paciente")
@CrossOrigin("*")
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

    @GetMapping("/")
    @Operation(summary = "consultar", description = "Metodo utilizado para consultar los pacientes")
    public List<ResumenPacienteDTO> obtenerPaciente() {
        return manejadorConsultarPacientes.ejecutarObtener();
    }

}
