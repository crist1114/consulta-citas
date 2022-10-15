package com.ceiba.paciente.adaptador.repositorio;

import com.ceiba.infraestructura.jdbc.MapperResult;
import com.ceiba.paciente.modelo.entidad.Paciente;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class MapeoPaciente implements RowMapper<Paciente>, MapperResult {

    @Override
    public Paciente mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        var id = resultSet.getLong("id");
        var nombre = resultSet.getString("nombre");
        var tipoPaciente = resultSet.getString("tipo_paciente");

        return Paciente.reconstruir(id, nombre,tipoPaciente);
    }
}
