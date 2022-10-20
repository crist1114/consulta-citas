package com.ceiba.cita.controlador;

import com.ceiba.ApplicationMock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ComandoControladorCita.class)
@ContextConfiguration(classes = ApplicationMock.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ConsultaControladorCitaPacienteTest {

    @Autowired
    private MockMvc mocMvc;

    @Test
    public void consultarCitaPaciente() throws Exception{

        mocMvc.perform(get("/cita/consultar-cita-paciente/"+1092)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("id", is(1)))
                .andExpect(jsonPath("idPaciente", is(1092)))
                .andExpect(jsonPath("tipoProcedimiento", is("LIMPIEZA")))
                .andExpect(jsonPath("valor", is(55000.0)))
                .andExpect(jsonPath("fecha", is("2022-10-19")))
                .andExpect(jsonPath("hora", is("15:00:00")))
                .andExpect(jsonPath("estado", is("NO_ATENDIDA")));
    }


}
