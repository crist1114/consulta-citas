package com.ceiba.cita.controlador;

import com.ceiba.cita.modelo.dto.ResumenCitaDTO;
import com.ceiba.factura.consulta.ManejadorConsultarCita;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cita")
@CrossOrigin("*")
@Tag(name = "Controlador consulta cita")
public class ConsultaControladorCita {

    private final ManejadorConsultarCita manejadorConsultarCita;

    public ConsultaControladorCita(ManejadorConsultarCita manejadorConsultarCita) {
        this.manejadorConsultarCita = manejadorConsultarCita;
    }

    @GetMapping("/consultar-por-paciente/{idPaciente}")
    @Operation(summary = "consultar", description = "Metodo utilizado para consultar la cita de un paciente")
    public ResumenCitaDTO obtenerCitaNoAtendidaPaciente(@PathVariable Long idPaciente) {

        return manejadorConsultarCita.ejecutar(idPaciente);
    }

    @GetMapping("/consultar")
    @Operation(summary = "consultar", description = "Metodo utilizado para consultar la cita de un paciente")
    public List<ResumenCitaDTO> obtenerCitas() {
        return manejadorConsultarCita.ejecutarObtener();
    }

    @GetMapping("/consultar-por-fecha/{fecha}")
    @Operation(summary = "consultar", description = "Metodo utilizado para consultar la cita de un paciente")
    public List<ResumenCitaDTO> obtenerCitasPorFecha(@PathVariable String fecha) {
        return manejadorConsultarCita.ejecutarObtenerCitasPorFecha(fecha);
    }

}
