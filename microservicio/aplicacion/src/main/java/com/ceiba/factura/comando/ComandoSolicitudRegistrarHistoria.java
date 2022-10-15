package com.ceiba.factura.comando;

import com.ceiba.historia.modelo.entidad.Ubicaciones;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ComandoSolicitudRegistrarHistoria {

    private Long idPaciente;
    private LocalDate fechaEmision;
    private Ubicaciones ubicacion;
}
