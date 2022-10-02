package com.ceiba.cita.adaptador;

import com.ceiba.cita.modelo.dto.ResumenCitaDTO;
import com.ceiba.cita.modelo.entidad.EstadoCita;
import com.ceiba.infraestructura.jdbc.MapperResult;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class MapeoCitaResumen implements RowMapper<ResumenCitaDTO>, MapperResult {

    @Override
    public ResumenCitaDTO mapRow(ResultSet resultSet, int rowNum) throws SQLException {
//
        var id = resultSet.getLong("id");
        var estado = EstadoCita.valueOf(resultSet.getString("estado"));

        return new ResumenCitaDTO(id,estado);
    }
}
