package com.ceiba.cita.adaptador.dao;

import com.ceiba.cita.adaptador.MapeoResumenCitaDTO;
import com.ceiba.cita.modelo.dto.ResumenCitaDTO;
import com.ceiba.cita.puerto.dao.DaoCita;
import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.EjecucionBaseDeDatos;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class DaoCitaMysql implements DaoCita {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    @SqlStatement(namespace = "cita", value = "obtenercitanoatendidapaciente")
    private static String sqlObtenerCitaNoAtendidaPaciente;

    @SqlStatement(namespace = "cita", value = "obtenercitas")
    private static String sqlObtenerCitas;

    @SqlStatement(namespace = "cita", value = "obtenercitasporfecha")
    private static String sqlObtenerCitasPorFecha;

    public DaoCitaMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @Override
    public ResumenCitaDTO obtenerCitaNoAtendidaPaciente(Long idPaciente) {

        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id_paciente", idPaciente);

        return EjecucionBaseDeDatos.obtenerUnObjetoONull(() ->
                this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlObtenerCitaNoAtendidaPaciente,
                        paramSource, new MapeoResumenCitaDTO()));
    }

    @Override
    public List<ResumenCitaDTO> obtenerCitas() {
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate()
                .query(sqlObtenerCitas, new MapeoResumenCitaDTO());
    }

    @Override
    public List<ResumenCitaDTO> obtenerCitasPorFecha(String fecha) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("fecha", fecha);

        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate()
                .query(sqlObtenerCitasPorFecha, paramSource, new MapeoResumenCitaDTO());
    }
}
