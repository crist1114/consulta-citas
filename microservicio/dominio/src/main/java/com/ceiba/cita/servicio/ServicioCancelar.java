package com.ceiba.cita.servicio;

import com.ceiba.cita.modelo.entidad.Cita;
import com.ceiba.cita.puerto.repositorio.RepositorioCita;
import com.ceiba.dominio.ValidadorArgumento;

public class ServicioCancelar {

    public final RepositorioCita repositorioCita;

    public ServicioCancelar(RepositorioCita repositorioCita) {
        this.repositorioCita = repositorioCita;
    }

    public void ejecutar(Cita cita){
        ValidadorArgumento.validarObligatorio(cita, "No existe una cita para cancelar");
        cita.cancelar();
        repositorioCita.actualizarEstado(cita);
    }
}
