package com.ceiba.cita.servicio;

import com.ceiba.cita.modelo.entidad.Cita;
import com.ceiba.cita.puerto.repositorio.RepositorioCita;
import com.ceiba.dominio.ValidadorArgumento;

public class ServicioConfirmar {
    public final RepositorioCita repositorioCita;

    public ServicioConfirmar(RepositorioCita repositorioCita) {
        this.repositorioCita = repositorioCita;
    }

    public void ejecutar(Cita cita){
        ValidadorArgumento.validarObligatorio(cita, "No existe una cita para confirmar");
        cita.confirmar();
        repositorioCita.actualizarEstado(cita);
    }
}
