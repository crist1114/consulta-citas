package com.ceiba.cita.modelo.entidad;

import com.ceiba.cita.modelo.dto.ResumenCitaDTO;
import com.ceiba.dominio.ValidadorArgumento;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.paciente.entidad.Paciente;

import java.sql.SQLOutput;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Cita {

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

    private Cita(Long id, Long idPaciente, TipoProcedimiento tipoProcedimiento, LocalDate fecha) {
        this.id = id;
        this.idPaciente = idPaciente;
        this.tipoProcedimiento = tipoProcedimiento;
        this.fecha = fecha;
    }

    public static Cita crear(SolicitudAgendar solicitudAgendar, Object fechaUltimaHistoria,
                             List<ResumenCitaDTO> resumenCitaDTOS) {

        ValidadorArgumento.validarObligatorio(solicitudAgendar.getIdPaciente(), "El paciente es requerido para agendar");
        ValidadorArgumento.validarObligatorio(solicitudAgendar.getTipoProcedimiento(), "El procedimiento es obligatorio");

        if(!existeValor(solicitudAgendar.getTipoProcedimiento().toString())){
            throw new ExcepcionValorInvalido("El tipo procedimiento no es valido");
        }
        if(fechaUltimaHistoria!=null) {
            fechaUltimaHistoria = LocalDate.parse(fechaUltimaHistoria.toString());

            if (solicitudAgendar.getTipoProcedimiento().equals(TipoProcedimiento.MANTENIMIENTO_DE_BRACKETS.toString())
                    && !historiaValidaParaCitaMantenimiento((LocalDate)fechaUltimaHistoria)) {
                throw new ExcepcionValorInvalido("Debe agendar cita para limpieza ya que su ultima historia registrada es mayor a 3 meses");
            }
        }
        if(yaTieneCita(resumenCitaDTOS))
            throw new ExcepcionDuplicidad("El paciente ya tiene una cita agendada");

        return new Cita(solicitudAgendar.getIdPaciente(), solicitudAgendar.getTipoProcedimiento());
    }

    private static boolean yaTieneCita(List<ResumenCitaDTO> resumenCitaDTOS) {
        return resumenCitaDTOS.stream().
                filter(x -> x.getEstado().equals(EstadoCita.NO_ATENDIDA)).collect(Collectors.toList()).size()>0;
    }

    private static boolean historiaValidaParaCitaMantenimiento(LocalDate fechaUltimaHisotia){

        if(fechaUltimaHisotia==null)
            return true;

        LocalDate hoy = LocalDate.now();

        Long meses = ChronoUnit.MONTHS.between(fechaUltimaHisotia, hoy);

        return meses<TIEMPO_MAX_VALIDO_HISTORIA_PARA_CITA_MANTENIMIENTO;
    }

    private static boolean existeValor(String valor){

        for (TipoProcedimiento tipo : TipoProcedimiento.values()){
            if(tipo.toString().equals(valor))
                return true;
        }
        return false;
    }

    public Long getId() {
        return id;
    }

    public Long getIdPaciente() {
        return idPaciente;
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
}
