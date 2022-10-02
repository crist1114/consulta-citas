package com.ceiba.cita.controlador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.factura.comando.ComandoSolicitudAgendar;
import com.ceiba.factura.comando.manejador.ManejadorAgendar;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLOutput;

@RestController
@RequestMapping("/cita")
@Tag(name = "Controlador comando cita")
public class ComandoControladorCita {

    private final ManejadorAgendar manejadorAgendar;


    public ComandoControladorCita(ManejadorAgendar manejadorAgendar) {
        this.manejadorAgendar = manejadorAgendar;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    @Operation(summary = "Agendar", description = "Metodo utilizado para crear una nueva cita")
    public ComandoRespuesta<Long> agendar(@RequestBody ComandoSolicitudAgendar comandoSolicitudAgendar) {

        return this.manejadorAgendar.ejecutar(comandoSolicitudAgendar);
    }
}
