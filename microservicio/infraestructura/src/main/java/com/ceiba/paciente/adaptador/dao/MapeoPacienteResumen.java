package com.ceiba.paciente.adaptador.dao;

import com.ceiba.infraestructura.jdbc.MapperResult;
import com.ceiba.paciente.modelo.dto.ResumenPacienteDTO;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class MapeoPacienteResumen implements RowMapper<ResumenPacienteDTO>, MapperResult {

    @Override
    public ResumenPacienteDTO mapRow(ResultSet resultSet, int rowNum) throws SQLException {

        var id = resultSet.getLong("id");
        var nombre = resultSet.getString("nombre");
        var tipoPaciente = resultSet.getString("tipo_paciente");

        return new ResumenPacienteDTO(id, nombre, tipoPaciente);
    }
}
