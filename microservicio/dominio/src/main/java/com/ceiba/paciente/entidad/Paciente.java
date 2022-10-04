package com.ceiba.paciente.entidad;

import com.ceiba.dominio.ValidadorArgumento;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;

public final class Paciente {

    private Long id;

    private final String nombre;

    private final TipoPaciente tipoPaciente;

    private Paciente(Long id, String nombre, String tipoPaciente) {
        this.id = id;
        this.nombre = nombre;
        this.tipoPaciente = TipoPaciente.valueOf(tipoPaciente);
    }

    public static Paciente crear(SolicitudRegistrar solicitudRegistrar){
        ValidadorArgumento.validarObligatorio(solicitudRegistrar.getId(), "El id es requerido");
        ValidadorArgumento.validarObligatorio(solicitudRegistrar.getNombre(), "El nombre es requerido");
        ValidadorArgumento.validarObligatorio(solicitudRegistrar.getTipoPaciente(), "El tipo paciente es requerido");
        ValidadorArgumento.validarValido(solicitudRegistrar.getTipoPaciente(), TipoPaciente.class, "El tipo paciente no es valido");

        return new Paciente(solicitudRegistrar.getId(), solicitudRegistrar.getNombre(), solicitudRegistrar.getTipoPaciente());
    }

    public static Paciente reconstruir(Long id, String nombre, String tipoPaciente) {

        ValidadorArgumento.validarObligatorio(id, "El id es requerido");
        ValidadorArgumento.validarObligatorio(tipoPaciente, "El tipo paciente es requerido");
        ValidadorArgumento.validarObligatorio(nombre, "El nombre es requerido");

        if(!existeValor(tipoPaciente)){
            throw new ExcepcionValorInvalido("El tipo paciente no es valido");
        }

        return new Paciente(id, nombre, tipoPaciente);
    }

    private static boolean existeValor(String valor){
        for (TipoPaciente tipo : TipoPaciente.values()){
            if(tipo.toString().equals(valor))
                return true;
        }
        return false;
    }

    public boolean esContributivo(){
        return this.tipoPaciente.toString().equals(TipoPaciente.CONTRIBUTIVO.toString());
    }

    public boolean esSubsidiado(){
        return this.tipoPaciente.toString().equals(TipoPaciente.SUBSIDIADO.toString());
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public TipoPaciente getTipoPaciente() {
        return tipoPaciente;
    }
}
