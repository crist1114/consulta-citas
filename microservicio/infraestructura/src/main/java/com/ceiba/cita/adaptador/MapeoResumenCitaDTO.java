package com.ceiba.cita.adaptador;

import com.ceiba.cita.modelo.dto.ResumenCitaDTO;
import com.ceiba.infraestructura.jdbc.MapperResult;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class MapeoResumenCitaDTO implements RowMapper<ResumenCitaDTO>, MapperResult {

    @Override
    public ResumenCitaDTO mapRow(ResultSet rs, int rowNum) throws SQLException {

        var id = rs.getLong("id");
        var idPaciente = rs.getLong("id_paciente");
        var tipoProcedimiento = rs.getString("tipo_procedimiento");
        var fecha = rs.getDate("fecha").toLocalDate();
        var hora = rs.getTime("hora").toLocalTime();
        var estado = rs.getString("estado");
        var valor = rs.getBigDecimal("valor");


        return new ResumenCitaDTO(id,idPaciente,tipoProcedimiento,fecha,hora,estado,valor);
    }
}
