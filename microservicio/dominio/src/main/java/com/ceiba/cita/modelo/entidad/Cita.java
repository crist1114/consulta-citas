package com.ceiba.cita.modelo.entidad;
import com.ceiba.dominio.ValidadorArgumento;
import com.ceiba.paciente.entidad.Paciente;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public final class Cita {

    private Long id;

    private Long idPaciente;

    private final TipoProcedimiento tipoProcedimiento;

    private LocalDate fecha;

    private EstadoCita estado;

    private static final int TIEMPO_MAX_VALIDO_HISTORIA_PARA_CITA_MANTENIMIENTO = 3;


    private Cita(Long idPaciente, String tipoProcedimiento){
        this.idPaciente = idPaciente;
        this.tipoProcedimiento = TipoProcedimiento.valueOf(tipoProcedimiento);
        this.fecha = LocalDate.now();
        this.estado = EstadoCita.NO_ATENDIDA;
    }

    private Cita(Long id, Long idPaciente, String tipoProcedimiento, LocalDate fecha, String estado) {
        this.id = id;
        this.idPaciente = idPaciente;
        this.tipoProcedimiento = TipoProcedimiento.valueOf(tipoProcedimiento);
        this.fecha = fecha;
        this.estado = EstadoCita.valueOf(estado);
    }

    public static Cita crear(SolicitudAgendar solicitudAgendar) {

        ValidadorArgumento.validarObligatorio(solicitudAgendar.getPaciente(), "El paciente no existe");
        ValidadorArgumento.validarObligatorio(solicitudAgendar.getTipoProcedimiento(), "El procedimiento es obligatorio");
        ValidadorArgumento.validarValido(solicitudAgendar.getTipoProcedimiento(), TipoProcedimiento.class,"El tipo procedimiento no es valido" );

        return new Cita(solicitudAgendar.getPaciente().getId(), solicitudAgendar.getTipoProcedimiento());
    }

    public static Cita reconstruir(Long id, Long idPaciente, String tipoProcedimiento, LocalDate fecha, String estado){


        ValidadorArgumento.validarObligatorio(id, "El id no es valido");
        ValidadorArgumento.validarObligatorio(idPaciente, "El id paciente es obligatorio");
        ValidadorArgumento.validarObligatorio(tipoProcedimiento, "El tipo procedimiento es obligatorio" );
        ValidadorArgumento.validarObligatorio(fecha, "La fecha es obligatoria");
        ValidadorArgumento.validarObligatorio(estado, "el estado es obligatorio");

        return new Cita(id,idPaciente,tipoProcedimiento,fecha,estado);
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
        return this.idPaciente;
    }

    public boolean esMantenimientoDeBrackets(){
        return this.tipoProcedimiento.equals(TipoProcedimiento.MANTENIMIENTO_DE_BRACKETS);
    }
}
