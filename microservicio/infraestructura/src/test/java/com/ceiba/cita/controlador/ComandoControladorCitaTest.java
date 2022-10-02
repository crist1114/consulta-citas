package com.ceiba.cita.controlador;

import com.ceiba.cita.modelo.entidad.TipoProcedimiento;
import com.ceiba.cita.puerto.repositorio.RepositorioCita;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ComandoControladorCitaTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mocMvc;

    @Autowired
    private RepositorioCita repositorioFactura;


//    @Test
//    void crearCitaExitoso() throws Exception {
//
//        var comandoAgendarTestDataBuilder = new ComandoAgendarTestDataBuilder().crearPorDefecto().build();
//
//        var resultado = mocMvc.perform(post("/api/v1/cita")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(comandoAgendarTestDataBuilder)))
//                .andExpect(status().is2xxSuccessful()).andReturn();
//
//        String jsonResult = resultado.getResponse().getContentAsString();
//        var respuesta = objectMapper.readValue(jsonResult, RespuestaAgendar.class);
//
//        var citaGuardada = repositorioFactura.obtener(respuesta.getId());
//
//        Assertions.assertEquals(1090l, citaGuardada.getIdPaciente());
//        Assertions.assertEquals(TipoProcedimiento.LIMPIEZA.toString(), citaGuardada.getTipoProcedimiento().toString());
//
//
//    }

}
