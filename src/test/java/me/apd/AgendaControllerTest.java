package me.apd;

import liquibase.integration.spring.SpringLiquibase;
import me.apd.controllers.AgendaController;
import me.apd.entities.Turno;
import me.apd.repositories.AgendaRepository;
import me.apd.repositories.EspecialidadRepository;
import me.apd.repositories.MedicoRepository;
import me.apd.repositories.PacienteRepository;
import me.apd.services.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.test.web.servlet.MockMvc;

import javax.sql.DataSource;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AgendaController.class)
public class AgendaControllerTest {

    @MockBean
    private AgendaRepository agendaRepository;
    @MockBean
    private EspecialidadRepository especialidadRepository;
    @MockBean
    private MedicoRepository medicoRepository;
    @MockBean
    private PacienteRepository pacienteRepository;
//    private Agenda agenda;

//    @BeforeEach
//    void setUp(){
//        controller = new AgendaController(agenda);
//
//    }

    @Test
    public void nuevoTurno(@Autowired MockMvc mockMvc) throws Exception {
        Turno turno = Turno.builder().id(1L).build();

        doReturn(turno).when(agendaRepository).save(any(Turno.class));

        mockMvc.perform(post("/agenda/")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void modificarTurno(@Autowired MockMvc mockMvc) throws Exception {
        Turno turno = Turno.builder().id(1L).build();

        doReturn(turno).when(agendaRepository).save(any(Turno.class));

        mockMvc.perform(put("/agenda/{id}", "1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void eliminarTurno(@Autowired MockMvc mockMvc) throws Exception {

        mockMvc.perform(delete("/agenda/{id}", "1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}")).andDo(print()).andExpect(status().isOk());
        verify(agendaRepository).deleteById(1L);
    }

    @TestConfiguration
    static class Config {

        @Bean
        public Agenda agenda(AgendaRepository repository) {
            return new AgendaImpl(repository);
        }

        @Bean
        public Usuario usuario(MedicoRepository medicoRepository, PacienteRepository pacienteRepository) {
            return new UsuarioImpl(medicoRepository, pacienteRepository);
        }

        @Bean
        public EspecialidadService especialidadService(EspecialidadRepository especialidadRepository) {
            return new EspecialidadServiceImpl(especialidadRepository);
        }

        @Bean
        public DataSource dataSource() {
            return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).generateUniqueName(true).build();
        }

        @Bean
        public SpringLiquibase liquibase(DataSource dataSource) {
            SpringLiquibase liquibase = new SpringLiquibase();
            liquibase.setDataSource(dataSource);
            liquibase.isDropFirst();
            liquibase.setChangeLog("classpath:/db/changelog/db.changelog-master.yaml");
            return liquibase;
        }
    }
}
