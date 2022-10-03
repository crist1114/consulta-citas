package com.ceiba.cita.modelo.entidad;
import com.ceiba.dominio.ValidadorArgumento;
import com.ceiba.paciente.entidad.Paciente;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Cita {

    private Long id;

    private Paciente paciente;

    private final TipoProcedimiento tipoProcedimiento;

    private LocalDate fecha;

    private EstadoCita estado;

    private static final int TIEMPO_MAX_VALIDO_HISTORIA_PARA_CITA_MANTENIMIENTO = 3;


    private Cita(Paciente paciente, String tipoProcedimiento){
        this.paciente = paciente;
        this.tipoProcedimiento = TipoProcedimiento.valueOf(tipoProcedimiento);
        this.fecha = LocalDate.now();
        this.estado = EstadoCita.NO_ATENDIDA;
    }

    private Cita(Long id, Paciente paciente, TipoProcedimiento tipoProcedimiento, LocalDate fecha) {
        this.id = id;
        this.paciente = paciente;
        this.tipoProcedimiento = tipoProcedimiento;
        this.fecha = fecha;
    }

    public static Cita crear(SolicitudAgendar solicitudAgendar) {

        ValidadorArgumento.validarObligatorio(solicitudAgendar.getPaciente(), "El paciente no existe");
        ValidadorArgumento.validarObligatorio(solicitudAgendar.getTipoProcedimiento(), "El procedimiento es obligatorio");
        ValidadorArgumento.validarValido(solicitudAgendar.getTipoProcedimiento(), TipoProcedimiento.class,"El tipo procedimiento no es valido" );

        return new Cita(solicitudAgendar.getPaciente(), solicitudAgendar.getTipoProcedimiento());
    }

    public boolean historiaValidaParaCitaMantenimiento(Object fechaUltimaHistoria){

        if(fechaUltimaHistoria==null)
            return true;
        LocalDate hoy = LocalDate.now();

        Long meses = ChronoUnit.MONTHS.between(LocalDate.parse(fechaUltimaHistoria.toString()), hoy);

        return meses<TIEMPO_MAX_VALIDO_HISTORIA_PARA_CITA_MANTENIMIENTO;
    }

    public Long getId() {
        return id;
    }

    public Paciente getPaciente() {
        return paciente;
    }
    public TipoProcedimiento getTipoProcedimiento() {
        return tipoProcedimiento;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public EstadoCita getEstado() {
        return estado;
    }

    public Long getIdPaciente(){
        return this.paciente.getId();
    }
    public boolean esMantenimientoDeBrackets(){
        return this.tipoProcedimiento.equals(TipoProcedimiento.MANTENIMIENTO_DE_BRACKETS);
    }
}
