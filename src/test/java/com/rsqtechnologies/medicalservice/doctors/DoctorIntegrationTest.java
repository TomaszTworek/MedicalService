package com.rsqtechnologies.medicalservice.doctors;


import com.rsqtechnologies.medicalservice.doctors.repository.DoctorRepository;
import com.rsqtechnologies.medicalservice.doctors.repository.entity.Doctor;
import com.rsqtechnologies.medicalservice.doctors.repository.entity.Specialization;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class DoctorIntegrationTest {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldCreateAndReturnDoctor() throws Exception {
        //given
        final String doctor = createExampleDoctor();

        //when
        this.mockMvc.perform(post("/doctors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(doctor))
                .andExpect(status().isOk());

        //then
        assertThat(this.doctorRepository.findAll()).hasSize(1);

        this.mockMvc.perform(get("/doctors"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].name").value("Jan"))
                .andExpect(jsonPath("$[0].specialization").value("DERMATOLOGISTS"));
    }


    @Test
    @Sql(value = "classpath:doctor-controller/init-patients.sql", executionPhase = BEFORE_TEST_METHOD)
    void shouldGetAllDoctors() throws Exception {
        //when then
        this.mockMvc.perform(get("/doctors"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].name").value("Tomek"))
                .andExpect(jsonPath("$[0].specialization").value("ENDOCRINOLOGISTS"))
                .andExpect(jsonPath("$[1].name").value("Piotr"))
                .andExpect(jsonPath("$[1].specialization").value("CARDIOLOGISTS"));
    }

    @Test
    @Sql(value = "classpath:doctor-controller/init-patients.sql", executionPhase = BEFORE_TEST_METHOD)
    void shouldUpdateADoctor() throws Exception {
        //given
        final String updateDoctor = createExampleUpdateDoctor();

        //when
        this.mockMvc.perform(put("/doctors/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(updateDoctor))
                .andExpect(status().isOk());

        //then
        assertThat(this.doctorRepository.findAll()).hasSize(2);
        final Doctor doctor = this.doctorRepository.findById(1L).get();
        assertThat(doctor.getName()).isEqualTo("Jan");
        assertThat(doctor.getSpecialization()).isEqualTo(Specialization.ENDOCRINOLOGISTS);
    }

    @Test
    @Sql(value = "classpath:doctor-controller/init-patients.sql", executionPhase = BEFORE_TEST_METHOD)
    void shouldDeleteDoctor() throws Exception {
        //when
        this.mockMvc.perform(delete("/doctors/{id}", 1L))
                .andExpect(status().isOk());

        //then
        assertThat(this.doctorRepository.findAll()).hasSize(1);
        final Doctor doctor = this.doctorRepository.findById(2L).get();
        assertThat(doctor.getName()).isEqualTo("Piotr");
        assertThat(doctor.getSpecialization()).isEqualTo(Specialization.CARDIOLOGISTS);
    }

    @Test
    @Sql(value = "classpath:doctor-controller/init-patients.sql", executionPhase = BEFORE_TEST_METHOD)
    void shouldReturn400WhenSpecializationIsIncorrect() throws Exception {
        //given
        final String doctor = createDoctorWithWrongSpecialization();

        //when
        this.mockMvc.perform(post("/doctors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(doctor))
                .andExpect(status().isBadRequest());
    }

    @Test
    @Sql(value = "classpath:doctor-controller/init-patients.sql", executionPhase = BEFORE_TEST_METHOD)
    void shouldReturn400WhenThereIsNoDoctorWithGivenId() throws Exception {

        //when then
        this.mockMvc.perform(get("/doctors/{id}", 10L))
                .andExpect(status().isBadRequest());
    }

    private String createExampleDoctor() {
        return """
                {
                     "name": "Jan",
                     "specialization": "DERMATOLOGISTS"
                }
                """;
    }

    private String createExampleUpdateDoctor() {
        return """
                {
                     "name": "Jan",
                     "specialization": "ENDOCRINOLOGISTS"
                }
                """;
    }


    private String createDoctorWithWrongSpecialization() {
        return """
                {
                     "name": "Jan",
                     "specialization": "TEST"
                }
                """;
    }
}
