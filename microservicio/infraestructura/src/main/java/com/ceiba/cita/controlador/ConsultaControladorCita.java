package com.ceiba.cita.controlador;

import com.ceiba.cita.modelo.dto.ResumenCitaDTO;
import com.ceiba.factura.consulta.ManejadorConsultarCita;
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

    @GetMapping("/consultar-cita-paciente/{idPaciente}")
    @Operation(summary = "consultar", description = "Metodo utilizado para consultar la cita de un paciente")
    public ResumenCitaDTO obtenerCitaNoAtendidaPaciente(@PathVariable Long idPaciente) {

        return manejadorConsultarCita.ejecutar(idPaciente);
    }
}
