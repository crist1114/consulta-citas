package com.ceiba.factura.comando;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ComandoSolicitudAgendar {

    private Long idPaciente;
    private String tipoProcedimiento;
    private LocalDate fecha;
    private LocalTime hora;
    private double valor;

}
