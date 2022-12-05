package com.ceiba.historia.controlador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.factura.comando.ComandoSolicitudRegistrarHistoria;
import com.ceiba.factura.comando.manejador.ManejadorRegistrarHistoria;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/historia")
@CrossOrigin("*")
@Tag(name = "Controlador comando historia")
public class ComandoControladorHistoria {

    private ManejadorRegistrarHistoria manejadorRegistrarHistoria;

    public ComandoControladorHistoria(ManejadorRegistrarHistoria manejadorRegistrarHistoria) {
        this.manejadorRegistrarHistoria = manejadorRegistrarHistoria;
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    @Operation(summary = "Registrar", description = "Metodo utilizado registrar una historia")
    public ComandoRespuesta<Long> registrarHistoria(@RequestBody ComandoSolicitudRegistrarHistoria comandoSolicitudRegistrarHistoria){

        return manejadorRegistrarHistoria.ejecutar(comandoSolicitudRegistrarHistoria);
    }
}
