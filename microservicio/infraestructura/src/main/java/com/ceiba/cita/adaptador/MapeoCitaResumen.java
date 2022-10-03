package com.ceiba.cita.adaptador;
import com.ceiba.infraestructura.jdbc.MapperResult;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class MapeoCitaResumen implements RowMapper<Long>, MapperResult {

    @Override
    public Long mapRow(ResultSet resultSet, int rowNum) throws SQLException {

        return resultSet.getLong("total");
    }
}
