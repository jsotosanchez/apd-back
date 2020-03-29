package me.apd;

import liquibase.integration.spring.SpringLiquibase;
import me.apd.controllers.AgendaController;
import me.apd.entities.Especialidad;
import me.apd.entities.Medico;
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

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
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

    @Test
    public void nuevoTurno(@Autowired MockMvc mockMvc) throws Exception {
        Turno turno = Turno.builder().id(1L).medico(Medico.builder().id(2L).build())
                .especialidad(Especialidad.builder().id(3L).build())
                .build();

        doReturn(turno).when(agendaRepository).save(eq(turno));

        mockMvc.perform(post("/agenda/")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":1}")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().json("{\"id\":1}"));
    }

    @Test
    public void modificarTurno(@Autowired MockMvc mockMvc) throws Exception {
        Turno turno = Turno.builder().id(1L).build();

        doReturn(turno).when(agendaRepository).save(eq(turno));

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

    @Test
    public void reservarTurno(@Autowired MockMvc mockMvc) throws Exception {
        Turno turno = Turno.builder().id(1L).build();

        doThrow().when(agendaRepository.findById(turno.getId()));
        doReturn(turno).when(agendaRepository).save(eq(turno));

        mockMvc.perform(put("/agenda/{id}/reservar", "1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}")).andDo(print()).andExpect(status().isOk());
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
