package com.ceiba.historia.adaptador.repositorio;

import com.ceiba.historia.modelo.entidad.Historia;
import com.ceiba.historia.puerto.RepositorioHistoria;
import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.EjecucionBaseDeDatos;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;


@Repository
public class RepositorioHistoriaMysql implements RepositorioHistoria {


    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    private MapeoHistoria mapeoHistoria;

    @SqlStatement(namespace = "historia", value = "obtenerhistoriaporpaciente")
    private static String sqlObtenerPorId;

    @SqlStatement(namespace = "historia", value = "guardarhistoria")
    private static String sqlGuardarHistoria;

    public RepositorioHistoriaMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate, MapeoHistoria mapeoHistoria) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
        this.mapeoHistoria = mapeoHistoria;
    }

    @Override
    public Object obtenerFechaReciente(Long idPaciente) {

        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id_paciente", idPaciente);

        return EjecucionBaseDeDatos.obtenerUnObjetoONull(() ->
                this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlObtenerPorId,
                        paramSource, mapeoHistoria));
    }

    @Override
    public Long guardar(Historia historia) {
        MapSqlParameterSource nombreParam = new MapSqlParameterSource();

        nombreParam.addValue("id_paciente",historia.getIdPaciente());
        nombreParam.addValue("fecha_emision" , historia.getFechaEmision());
        nombreParam.addValue("ubicacion", historia.getUbicacion().toString());

        return this.customNamedParameterJdbcTemplate.crear(nombreParam, sqlGuardarHistoria);
    }
}
