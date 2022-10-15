package com.ceiba.historia.controlador;

import com.ceiba.ApplicationMock;
import com.ceiba.factura.comando.ComandoSolicitudRegistrarHistoria;
import com.ceiba.historia.modelo.entidad.Ubicaciones;
import com.ceiba.historia.puerto.RepositorioHistoria;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ComandoControladorHistoria.class)
@ContextConfiguration(classes = ApplicationMock.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ComandoRegistrarHistoriaTest {

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mocMvc;
    @Autowired
    private RepositorioHistoria repositorioHistoria;

    @Test
    public void crearHistoriaExitoso() throws Exception{

        ComandoSolicitudRegistrarHistoria comanandoSolicitudHistoriaTestDataBuilder = new ComandoRegistrarHistoriaTestDataBuilder()
                .conIdPaciente(1092l)
                .conFechaEmision(LocalDate.of(2022,10,10))
                .conUbicacion(Ubicaciones.CASILLERO_1.toString())
                .build();

        mocMvc.perform(post("/historia")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(comanandoSolicitudHistoriaTestDataBuilder)))
                .andExpect(status().is2xxSuccessful()).andReturn();


        var historiaFechaUltimaGuardada = repositorioHistoria.obtenerFechaReciente(1092l);

        Assertions.assertEquals("2022-10-10", historiaFechaUltimaGuardada.toString());
    }

}
