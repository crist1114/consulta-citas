package com.ceiba.paciente.adaptador.repositorio;

import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.EjecucionBaseDeDatos;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.paciente.entidad.Paciente;
import com.ceiba.paciente.puerto.repositorio.RepositorioPaciente;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public class RepositorioPacienteMysql implements RepositorioPaciente {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    @SqlStatement(namespace = "paciente", value = "obtenerpacienteporid")
    private static String sqlObtenerPorId;

    @SqlStatement(namespace = "paciente", value = "guardarpaciente")
    private static String sqlGuardar;

    public RepositorioPacienteMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @Override
    public Paciente obtener(Long id) {

        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id", id);

        return EjecucionBaseDeDatos.obtenerUnObjetoONull(() ->
                this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlObtenerPorId,
                        paramSource, new MapeoPaciente()));
    }

    @Override
    public Long guardar(Paciente paciente) {
        MapSqlParameterSource nombreParam = new MapSqlParameterSource();

        nombreParam.addValue("id", paciente.getId());
        nombreParam.addValue("nombre" , paciente.getNombre());
        nombreParam.addValue("tipo_paciente", paciente.getTipoPaciente().toString());

        return this.customNamedParameterJdbcTemplate.crear(nombreParam, sqlGuardar);
    }
}
