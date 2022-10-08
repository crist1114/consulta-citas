package com.ceiba.cita.controlador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.factura.comando.ComandoCancelar;
import com.ceiba.factura.comando.ComandoSolicitudAgendar;
import com.ceiba.factura.comando.manejador.ManejadorAgendar;
import com.ceiba.factura.comando.manejador.ManejadorCancelar;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cita")
@Tag(name = "Controlador comando cita")
public class ComandoControladorCita {

    private final ManejadorAgendar manejadorAgendar;

    private final ManejadorCancelar manejadorCancelar;


    public ComandoControladorCita(ManejadorAgendar manejadorAgendar, ManejadorCancelar manejadorCancelar) {
        this.manejadorAgendar = manejadorAgendar;
        this.manejadorCancelar = manejadorCancelar;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    @Operation(summary = "Agendar", description = "Metodo utilizado para crear una nueva cita")
    public ComandoRespuesta<Long> agendar(@RequestBody ComandoSolicitudAgendar comandoSolicitudAgendar) {

        return this.manejadorAgendar.ejecutar(comandoSolicitudAgendar);
    }

    @PostMapping("cancelar/{id-cita}")
    @Operation(summary = "Anular", description = "Metodo utilizado para cancelar una cita")
    public void anular(@PathVariable("id-cita") Long idCita) {
        this.manejadorCancelar.ejecutar(new ComandoCancelar(idCita));
    }
}
