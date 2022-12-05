package com.ceiba.paciente.adaptador.dao;

import com.ceiba.cita.adaptador.MapeoResumenCitaDTO;
import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.EjecucionBaseDeDatos;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.paciente.modelo.dto.ResumenPacienteDTO;
import com.ceiba.paciente.puerto.dao.DaoPaciente;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DaoPacienteMysql implements DaoPaciente {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    @SqlStatement(namespace = "paciente", value="obtenerpacienteporid")
    private static String sqlObtenerPaciente;

    @SqlStatement(namespace = "paciente", value="obtenerpacientes")
    private static String sqlObtenerPacientes;

    public DaoPacienteMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @Override
    public ResumenPacienteDTO obtenerPaciente(Long id) {

        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id", id);

        return EjecucionBaseDeDatos.obtenerUnObjetoONull(() ->
                this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlObtenerPaciente,
                        paramSource, new MapeoPacienteResumen()));
    }
    @Override
    public List<ResumenPacienteDTO> obtenerPacientes() {
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate()
                .query(sqlObtenerPacientes, new MapeoPacienteResumen());
    }
}
