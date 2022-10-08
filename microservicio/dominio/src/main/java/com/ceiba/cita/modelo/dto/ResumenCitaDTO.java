package com.ceiba.cita.modelo.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
@AllArgsConstructor
@Getter
public class ResumenCitaDTO {

    private Long id;
    private Long idPaciente;
    private String tipoProcedimiento;
    private LocalDate fecha;
    private LocalTime hora;
    private String estado;
    private BigDecimal valor;
}
