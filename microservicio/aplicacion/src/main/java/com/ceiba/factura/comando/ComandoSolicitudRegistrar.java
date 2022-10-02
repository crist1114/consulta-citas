package com.ceiba.factura.comando;

import com.ceiba.paciente.entidad.TipoPaciente;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ComandoSolicitudRegistrar {

    private Long id;
    private String nombre;
    private String tipoPaciente;


}
