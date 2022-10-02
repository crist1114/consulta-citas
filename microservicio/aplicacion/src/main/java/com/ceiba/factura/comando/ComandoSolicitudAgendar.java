package com.ceiba.factura.comando;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ComandoSolicitudAgendar {

    private Long id_paciente;

    private String tipo_procedimiento;

}
