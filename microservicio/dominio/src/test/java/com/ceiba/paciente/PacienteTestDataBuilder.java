package com.ceiba.paciente;

import com.ceiba.paciente.modelo.entidad.Paciente;
import com.ceiba.paciente.modelo.entidad.TipoPaciente;

public class PacienteTestDataBuilder {
    private Long id;
    private String nombre;
    private String tipoPaciente;


    public PacienteTestDataBuilder conPacientePorDefecto(){
        this.nombre = "Paciente 1";
        this.tipoPaciente = TipoPaciente.CONTRIBUTIVO.toString();
        this.id = 1091l;
        return this;
    }

    public PacienteTestDataBuilder conNombre(String nombre){
        this.nombre = nombre;
        return this;
    }

    public PacienteTestDataBuilder conTipoPaciente(TipoPaciente tipoPaciente){
        this.tipoPaciente = tipoPaciente.toString();
        return this;
    }

    public PacienteTestDataBuilder conId(Long id){
        this.id = id;
        return this;
    }

    public Paciente reconstruir() {
        return Paciente.reconstruir(id, nombre, tipoPaciente);
    }
}
