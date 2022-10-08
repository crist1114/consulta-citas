package com.ceiba.configuracion;

import com.ceiba.cita.puerto.repositorio.RepositorioCita;
import com.ceiba.cita.servicio.ServicioAgendar;
import com.ceiba.cita.servicio.ServicioCancelar;
import com.ceiba.historia.puerto.RepositorioHistoria;
import com.ceiba.paciente.puerto.repositorio.RepositorioPaciente;
import com.ceiba.paciente.servicio.ServicioRegistrar;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanServicio {


    @Bean
    public ServicioAgendar servicioAgendar(RepositorioCita repositorioCita, RepositorioHistoria repositorioHistoria){
        return new ServicioAgendar(repositorioCita, repositorioHistoria);
    }

    @Bean
    public ServicioRegistrar servicioRegistrar(RepositorioPaciente repositorioPaciente){
        return new ServicioRegistrar(repositorioPaciente);
    }
    @Bean
    public ServicioCancelar servicioCancelar(RepositorioCita repositorioCita){
        return new ServicioCancelar(repositorioCita);
    }





}
