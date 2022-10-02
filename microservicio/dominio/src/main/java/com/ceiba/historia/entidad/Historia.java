package com.ceiba.historia.entidad;

import com.ceiba.dominio.ValidadorArgumento;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.paciente.entidad.Paciente;

import java.time.LocalDate;
import java.util.Date;

public class Historia {

    private Long id;

    private final LocalDate fecha_emision;

    private final Ubicaciones ubicacion;

    private Long idPaciente;

    public Historia(Long id, LocalDate fecha_emision, String ubicacion, Long idPaciente) {
        this.id = id;
        this.fecha_emision = fecha_emision;
        this.ubicacion = Ubicaciones.valueOf(ubicacion);
        this.idPaciente = idPaciente;
    }

    public static Historia reconstruir(Long id, LocalDate fecha_emision, String ubicacion, Long idPaciente) {

        ValidadorArgumento.validarObligatorio(id, "El id es requerido");
        ValidadorArgumento.validarObligatorio(fecha_emision, "La fecha de emision es requerida");
        ValidadorArgumento.validarObligatorio(ubicacion, "La ubicacion es requerida");
        ValidadorArgumento.validarObligatorio(idPaciente, "El id del paciente es requerido");

        if(!existeValor(ubicacion)){
            throw new ExcepcionValorInvalido("La ubicacion no es valida");
        }

        return new Historia(id, fecha_emision, ubicacion, idPaciente);
    }

    private static boolean existeValor(String valor){
        for (Ubicaciones ubicacion : Ubicaciones.values()){
            if(ubicacion.toString().equals(valor))
                return true;
        }
        return false;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getFecha_emision() {
        return fecha_emision;
    }

    public Ubicaciones getUbicacion() {
        return ubicacion;
    }

    public Long getIdPaciente() {
        return idPaciente;
    }
}
