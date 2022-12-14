package com.ceiba.historia.adaptador.repositorio;

import com.ceiba.infraestructura.jdbc.MapperResult;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class MapeoHistoria implements RowMapper<Object>, MapperResult {



    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {

      return rs.getDate("fecha_emision");
    }
}
