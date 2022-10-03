package com.ceiba.cita.adaptador;

import com.ceiba.cita.modelo.entidad.Cita;
import com.ceiba.infraestructura.jdbc.MapperResult;
import com.ceiba.paciente.puerto.repositorio.RepositorioPaciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MapeoCita implements RowMapper<Cita>, MapperResult {



    @Override
    public Cita mapRow(ResultSet rs, int rowNum) throws SQLException {

        var id = rs.getLong("id");
        var idPaciente = rs.getLong("id_paciente");
        var tipoProcedimiento = rs.getString("tipo_procedimiento");
        var fecha = rs.getDate("fecha").toLocalDate();
        var estado = rs.getString("estado");

        return Cita.reconstruir(id,idPaciente,tipoProcedimiento,fecha,estado);
    }
}
