package com.ceiba.factura.comando.manejador;

import com.ceiba.cita.puerto.repositorio.RepositorioCita;
import com.ceiba.cita.servicio.ServicioCancelar;
import com.ceiba.cita.servicio.ServicioConfirmar;
import com.ceiba.factura.comando.ComandoConfirmar;
import com.ceiba.manejador.ManejadorComando;
import org.springframework.stereotype.Component;

@Component
public class ManejadorConfirmar implements ManejadorComando<ComandoConfirmar> {

    private final ServicioConfirmar servicioConfirmar;

    private final RepositorioCita repositorioCita;

    public ManejadorConfirmar(ServicioConfirmar servicioConfirmar, RepositorioCita repositorioCita) {
        this.servicioConfirmar = servicioConfirmar;
        this.repositorioCita = repositorioCita;
    }

    @Override
    public void ejecutar(ComandoConfirmar comando) {
        servicioConfirmar.ejecutar(repositorioCita.obtener(comando.getIdCita()));
    }
}
