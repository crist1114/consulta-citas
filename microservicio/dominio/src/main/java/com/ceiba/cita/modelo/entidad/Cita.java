package com.ceiba.cita.modelo.entidad;
import com.ceiba.dominio.ValidadorArgumento;
import com.ceiba.paciente.modelo.entidad.TipoPaciente;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public final class Cita {

    public static final double VALOR_TIPO_CONTRIBUTIVO = 55000;
    public static final double VALOR_TIPO_SUBSIDIADO = 3500;
    private static final int TIEMPO_MAX_VALIDO_HISTORIA_PARA_CITA_MANTENIMIENTO = 3;


    private Long id;

    private Long idPaciente;

    private final TipoProcedimiento tipoProcedimiento;

    private LocalDate fecha;

    private LocalTime hora;
    private EstadoCita estado;

    private BigDecimal valor;

    private Cita(Long idPaciente, String tipoProcedimiento, BigDecimal valor, LocalDate fecha, LocalTime hora){
        this.idPaciente = idPaciente;
        this.tipoProcedimiento = TipoProcedimiento.valueOf(tipoProcedimiento);
        this.fecha = fecha;
        this.estado = EstadoCita.NO_ATENDIDA;
        this.valor = valor;
        this.hora = hora;
    }

    private Cita(Long id, Long idPaciente, String tipoProcedimiento, LocalDate fecha, LocalTime hora, String estado, BigDecimal valor) {
        this.id = id;
        this.idPaciente = idPaciente;
        this.tipoProcedimiento = TipoProcedimiento.valueOf(tipoProcedimiento);
        this.fecha = fecha;
        this.hora = hora;
        this.estado = EstadoCita.valueOf(estado);
        this.valor = valor;
    }

    public static Cita crear(SolicitudAgendar solicitudAgendar) {

        ValidadorArgumento.validarObligatorio(solicitudAgendar.getPaciente(), "El paciente no existe");
        ValidadorArgumento.validarObligatorio(solicitudAgendar.getTipoProcedimiento(), "El procedimiento es obligatorio");
        ValidadorArgumento.validarValido(solicitudAgendar.getTipoProcedimiento(), TipoProcedimiento.class,"El tipo procedimiento no es valido" );
        ValidadorArgumento.validarObligatorio(solicitudAgendar.getValor(),"Debe ingresar el monto de la cita");
        ValidadorArgumento.validarPositivo(solicitudAgendar.getValor().doubleValue(),"No se permiten valores negativos");
        ValidadorArgumento.validarObligatorio(solicitudAgendar.getFecha(),"Debe ingresar una fecha");
        ValidadorArgumento.validarObligatorio(solicitudAgendar.getHora(),"Debe ingresar una hora");

        return new Cita(solicitudAgendar.getPaciente().getId(), solicitudAgendar.getTipoProcedimiento(), solicitudAgendar.getValor(), solicitudAgendar.getFecha(), solicitudAgendar.getHora());
    }

    public static Cita reconstruir(Long id, Long idPaciente, String tipoProcedimiento, LocalDate fecha, LocalTime hora, String estado, BigDecimal valor){


        ValidadorArgumento.validarObligatorio(id, "El id no es valido");
        ValidadorArgumento.validarObligatorio(idPaciente, "El id paciente es obligatorio");
        ValidadorArgumento.validarObligatorio(tipoProcedimiento, "El tipo procedimiento es obligatorio" );
        ValidadorArgumento.validarObligatorio(fecha, "La fecha es obligatoria");
        ValidadorArgumento.validarObligatorio(estado, "el estado es obligatorio");
        ValidadorArgumento.validarValido(tipoProcedimiento, TipoProcedimiento.class,"El tipo procedimiento no es valido" );
        ValidadorArgumento.validarObligatorio(valor,"Debe ingresar el monto de la cita");
        ValidadorArgumento.validarObligatorio(hora,"Debe ingresar una hora");

        return new Cita(id,idPaciente,tipoProcedimiento,fecha,hora,estado,valor);
    }

    public void cancelar() {
        this.estado = EstadoCita.CANCELADA;
    }

    public void confirmar(){this.estado = EstadoCita.ATENDIDA;}

    public boolean historiaValidaParaCitaMantenimiento(Object fechaUltimaHistoria){

        if(fechaUltimaHistoria==null)
            return true;
        LocalDate hoy = LocalDate.now();

        Long meses = ChronoUnit.MONTHS.between(LocalDate.parse(fechaUltimaHistoria.toString()), hoy);

        return meses<TIEMPO_MAX_VALIDO_HISTORIA_PARA_CITA_MANTENIMIENTO;
    }

    public BigDecimal getValorPorTipo(String tipoProcedimiento) {
        return tipoProcedimiento.equals(TipoPaciente.CONTRIBUTIVO.toString())
                ? BigDecimal.valueOf(VALOR_TIPO_CONTRIBUTIVO)
                : BigDecimal.valueOf(VALOR_TIPO_SUBSIDIADO);
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
    public LocalTime getHora() {
        return hora;
    }
    public int getDia(){
        return this.fecha.getDayOfWeek().getValue();
    }
    public int getSoloHora(){return this.getHora().getHour();}

    public BigDecimal getValor() {
        return valor;
    }
}
