package com.ceiba.historia.adaptador.repositorio;

import com.ceiba.historia.entidad.Historia;
import com.ceiba.historia.puerto.RepositorioHistoria;
import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.EjecucionBaseDeDatos;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.paciente.adaptador.repositorio.MapeoPaciente;
import com.ceiba.paciente.entidad.Paciente;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public class RepositorioHistoriaMysql implements RepositorioHistoria {


    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    private MapeoHistoria mapeoHistoria;

    @SqlStatement(namespace = "historia", value = "obtenerhistoriaporpaciente")
    private static String sqlObtenerPorId;

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
}
