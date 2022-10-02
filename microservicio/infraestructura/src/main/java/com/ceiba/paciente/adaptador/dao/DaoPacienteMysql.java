package com.ceiba.paciente.adaptador.dao;

import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.EjecucionBaseDeDatos;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.paciente.adaptador.repositorio.MapeoPacienteResumen;
import com.ceiba.paciente.entidad.ResumenPacienteDTO;
import com.ceiba.paciente.puerto.dao.DaoPaciente;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class DaoPacienteMysql implements DaoPaciente {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;
    private final MapeoPacienteResumen mapeoPacienteResumen;

    public DaoPacienteMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate, MapeoPacienteResumen mapeoPacienteResumen) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
        this.mapeoPacienteResumen = mapeoPacienteResumen;
    }

    @SqlStatement(namespace = "paciente", value = "obtenerporid")
    private static String sqlObtenerPorId;
    @Override
    public ResumenPacienteDTO obtenerPaciente(Long id) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id", id);
        return EjecucionBaseDeDatos.obtenerUnObjetoONull(() -> this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate()
                .queryForObject(sqlObtenerPorId, paramSource, mapeoPacienteResumen));
    }
}
