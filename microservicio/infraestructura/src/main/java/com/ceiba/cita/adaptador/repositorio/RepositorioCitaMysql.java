package com.ceiba.cita.adaptador.repositorio;

import com.ceiba.cita.adaptador.MapeoCitaResumen;
import com.ceiba.cita.modelo.dto.ResumenCitaDTO;
import com.ceiba.cita.modelo.entidad.Cita;
import com.ceiba.cita.puerto.repositorio.RepositorioCita;
import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.EjecucionBaseDeDatos;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.paciente.adaptador.repositorio.MapeoPaciente;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RepositorioCitaMysql implements RepositorioCita {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;


    public RepositorioCitaMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @SqlStatement(namespace = "cita", value = "guardarcita")
    private static String sqlGuardar;

    @SqlStatement(namespace = "cita", value = "obteneragendadas")
    private static String sqlObtenerAgendadas;


    @Override
    public Cita obtener(Long id) {

        return null;

    }

    @Override
    public Long guardar(Cita cita) {
        MapSqlParameterSource nombreParam = new MapSqlParameterSource();

        nombreParam.addValue("id_paciente",cita.getIdPaciente());
        nombreParam.addValue("tipo_procedimiento" , cita.getTipoProcedimiento().toString());
        nombreParam.addValue("fecha", cita.getFecha());
        nombreParam.addValue("estado", cita.getEstado().toString());

        return this.customNamedParameterJdbcTemplate.crear(nombreParam, sqlGuardar);
    }

    @Override
    public List<ResumenCitaDTO> ObtenerCitasAgendadasPaciente(Long id_paciente) {

        MapSqlParameterSource paramSource = new MapSqlParameterSource();

        paramSource.addValue("id_paciente", id_paciente);

        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate()
                .query(sqlObtenerAgendadas, paramSource, new MapeoCitaResumen());

    }
}
