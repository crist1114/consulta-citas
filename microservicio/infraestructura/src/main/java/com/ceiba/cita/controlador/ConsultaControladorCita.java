package com.ceiba.cita.controlador;

import com.ceiba.cita.modelo.dto.ResumenCitaDTO;
import com.ceiba.factura.comando.manejador.ManejadorConsultarCita;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cita")
@Tag(name = "Controlador consulta cita")
public class ConsultaControladorCita {

    private final ManejadorConsultarCita manejadorConsultarCita;

    public ConsultaControladorCita(ManejadorConsultarCita manejadorConsultarCita) {
        this.manejadorConsultarCita = manejadorConsultarCita;
    }

    @GetMapping("/consultar-cita-paciente/{id_paciente}")
    @Operation(summary = "consultar", description = "Metodo utilizado para consultar la cita de un paciente")
    public ResumenCitaDTO obtenerPaciente(@PathVariable Long id_paciente) {

        return manejadorConsultarCita.ejecutar(id_paciente);
    }
}
