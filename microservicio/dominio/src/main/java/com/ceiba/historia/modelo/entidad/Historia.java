package com.ceiba.historia.modelo.entidad;

import com.ceiba.dominio.ValidadorArgumento;

import java.time.LocalDate;

public final class Historia {

    private Long id;
    private Long idPaciente;
    private LocalDate fechaEmision;
    private Ubicaciones ubicacion;

    private Historia(Long idPaciente, LocalDate fechaEmision, Ubicaciones ubicacion) {

        this.idPaciente = idPaciente;
        this.fechaEmision = fechaEmision;
        this.ubicacion =ubicacion;
    }

    private Historia(Long id, Long idPaciente, LocalDate fechaEmision, String ubicacion) {
        this.id = id;
        this.idPaciente = idPaciente;
        this.fechaEmision = fechaEmision;
        this.ubicacion = Ubicaciones.valueOf(ubicacion);
    }

    public static Historia crear(SolicitudRegistrarHistoria solicitudRegistrarHistoria){

        ValidadorArgumento.validarObligatorio(solicitudRegistrarHistoria.getPaciente(), "Debe ingresar un id de paciente");
        ValidadorArgumento.validarObligatorio(solicitudRegistrarHistoria.getFechaEmision(), "Debe ingresar una fecha de emision");
        ValidadorArgumento.validarObligatorio(solicitudRegistrarHistoria.getUbicacion(), "Debe ingresar una ubicación");

        ValidadorArgumento.validarValido(solicitudRegistrarHistoria.getUbicacion().toString(), Ubicaciones.class,"La ubicacion no es valida" );

        return new Historia(solicitudRegistrarHistoria.getPaciente().getId(), solicitudRegistrarHistoria.getFechaEmision(), solicitudRegistrarHistoria.getUbicacion());
    }

    public static Historia reconstruir(Long id, Long idPaciente, LocalDate fechaEmision, String ubicacion){

        ValidadorArgumento.validarObligatorio(id, "Debe tener un id");
        ValidadorArgumento.validarObligatorio(idPaciente, "Debe ingresar un id de paciente");
        ValidadorArgumento.validarObligatorio(id, "Debe ingresar una fecha de emision");
        ValidadorArgumento.validarObligatorio(id, "Debe ingresar una ubicación");
        ValidadorArgumento.validarValido(ubicacion, Ubicaciones.class,"La ubicacion no es valida" );


        return new Historia(id, idPaciente, fechaEmision, ubicacion);
    }

    public Long getId() {
        return id;
    }

    public Long getIdPaciente() {
        return idPaciente;
    }

    public LocalDate getFechaEmision() {
        return fechaEmision;
    }

    public Ubicaciones getUbicacion() {
        return ubicacion;
    }
}
