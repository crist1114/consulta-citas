package com.ceiba.configuracion;

import com.ceiba.cita.puerto.repositorio.RepositorioCita;
import com.ceiba.cita.servicio.ServicioAgendar;
import com.ceiba.historia.puerto.RepositorioHistoria;
import com.ceiba.paciente.puerto.RepositorioPaciente;
import com.ceiba.paciente.servicio.ServicioRegistrar;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanServicio {


    @Bean
    public ServicioAgendar servicioAgendar(RepositorioCita repositorioCita, RepositorioHistoria repositorioHistoria, RepositorioPaciente repositorioPaciente){
        return new ServicioAgendar(repositorioCita, repositorioHistoria, repositorioPaciente);
    }

    @Bean
    public ServicioRegistrar servicioRegistrar(RepositorioPaciente repositorioPaciente){
        return new ServicioRegistrar(repositorioPaciente);
    }





}
