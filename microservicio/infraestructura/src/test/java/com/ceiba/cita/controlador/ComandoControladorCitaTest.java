package com.ceiba.cita.controlador;

import com.ceiba.ApplicationMock;
import com.ceiba.cita.modelo.entidad.TipoProcedimiento;
import com.ceiba.cita.puerto.repositorio.RepositorioCita;
import com.ceiba.factura.comando.ComandoSolicitudAgendar;
import com.ceiba.paciente.puerto.repositorio.RepositorioPaciente;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@ExtendWith(SpringExtension.class)
@WebMvcTest(ComandoControladorCita.class)
@ContextConfiguration(classes = ApplicationMock.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ComandoControladorCitaTest {

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mocMvc;
    @Autowired
    private RepositorioCita repositorioCita;
    @Autowired
    private RepositorioPaciente repositorioPaciente;


    @Test
    void crearCitaExitoso() throws Exception {

        ComandoSolicitudAgendar comandoAgendarTestDataBuilder = new ComandoAgendarTestDataBuilder()
                .crearPorDefecto()
                .conFecha(LocalDate.now().plusDays(2))
                .conHora(LocalTime.of(15, 00,00))
                .conIdPaciente(1093l)
                .build();

        var resultado = mocMvc.perform(post("/cita")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(comandoAgendarTestDataBuilder)))
                .andExpect(status().is2xxSuccessful()).andReturn();

        String jsonResult = resultado.getResponse().getContentAsString();

        var respuesta = objectMapper.readValue(jsonResult, RespuestaAgendar.class);

        var citaGuardada = repositorioCita.obtener(respuesta.getValor());

        Assertions.assertEquals(2l, citaGuardada.getId());
        Assertions.assertEquals(comandoAgendarTestDataBuilder.getIdPaciente(), citaGuardada.getIdPaciente());
        Assertions.assertEquals(TipoProcedimiento.LIMPIEZA.toString(), citaGuardada.getTipoProcedimiento().toString());

    }

    @Test
    void crearCitaDeberiaLanzarExcepcionPacienteNoExiste() throws Exception {

        ComandoSolicitudAgendar comandoAgendarTestDataBuilder = new ComandoAgendarTestDataBuilder()
                .crearPorDefecto()
                .conFecha(LocalDate.now().plusDays(2))
                .conHora(LocalTime.of(15, 00,00))
                .conIdPaciente(1090l)
                .build();

        mocMvc.perform(post("/cita")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(comandoAgendarTestDataBuilder)))
                .andExpect(status().is4xxClientError()).andReturn();
    }

}
