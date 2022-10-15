package com.ceiba.factura.comando.manejador;

import com.ceiba.cita.puerto.repositorio.RepositorioCita;
import com.ceiba.cita.servicio.ServicioCancelar;
import com.ceiba.factura.comando.ComandoCancelar;
import com.ceiba.manejador.ManejadorComando;
import org.springframework.stereotype.Component;

@Component
public class ManejadorCancelar implements ManejadorComando<ComandoCancelar> {

    private final ServicioCancelar servicioCancelar;

    private final RepositorioCita repositorioCita;


    public ManejadorCancelar(ServicioCancelar servicioCancelar, RepositorioCita repositorioCita) {
        this.servicioCancelar = servicioCancelar;
        this.repositorioCita = repositorioCita;
    }

    @Override
    public void ejecutar(ComandoCancelar comando) {
       servicioCancelar.ejecutar(repositorioCita.obtener(comando.getIdCita()));
    }
}
