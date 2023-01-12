package com.rsqtechnologies.medicalservice.patients;


import com.rsqtechnologies.medicalservice.patients.repository.PatientRepository;
import com.rsqtechnologies.medicalservice.patients.repository.entity.Patient;
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
class PatientIntegrationTest {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldCreateAndReturnPatient() throws Exception {
        //given
        final String patient = createExamplePatient();

        //when
        this.mockMvc.perform(post("/patients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(patient))
                .andExpect(status().isOk());

        //then
        assertThat(this.patientRepository.findAll()).hasSize(1);

        this.mockMvc.perform(get("/patients"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].firstName").value("Zbigniew"))
                .andExpect(jsonPath("$[0].lastName").value("Kowalski"));
    }


    @Test
    @Sql(value = "classpath:patient-controller/init-patients.sql", executionPhase = BEFORE_TEST_METHOD)
    void shouldGetAllPatients() throws Exception {
        //when then
        this.mockMvc.perform(get("/patients"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].firstName").value("Zbigniew"))
                .andExpect(jsonPath("$[0].lastName").value("Kowalski"))
                .andExpect(jsonPath("$[0].addressDto.city").value("Warszawa"))
                .andExpect(jsonPath("$[1].firstName").value("Jan"))
                .andExpect(jsonPath("$[1].lastName").value("Nowak"))
                .andExpect(jsonPath("$[1].addressDto.city").value("Poznan"));

    }

    @Test
    @Sql(value = "classpath:patient-controller/init-patients.sql", executionPhase = BEFORE_TEST_METHOD)
    void shouldUpdateAPatient() throws Exception {
        //given
        final String updatePatient = createExampleUpdatePatient();

        //when
        this.mockMvc.perform(put("/patients/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatePatient))
                .andExpect(status().isOk());

        //then
        assertThat(this.patientRepository.findAll()).hasSize(2);
        final Patient patient = this.patientRepository.findById(1L).get();
        assertThat(patient.getFirstName()).isEqualTo("Tomasz");
        assertThat(patient.getLastName()).isEqualTo("Testowy");
        assertThat(patient.getAddress().getStreet()).isEqualTo("testowa");
        assertThat(patient.getAddress().getPostCode()).isEqualTo("00-122");
        assertThat(patient.getAddress().getCity()).isEqualTo("Warszawa2");
    }

    @Test
    @Sql(value = "classpath:patient-controller/init-patients.sql", executionPhase = BEFORE_TEST_METHOD)
    void shouldDeletePatient() throws Exception {
        //when
        this.mockMvc.perform(delete("/patients/{id}", 1L))
                .andExpect(status().isOk());

        //then
        assertThat(this.patientRepository.findAll()).hasSize(1);
        final Patient patient = this.patientRepository.findById(2L).get();
        assertThat(patient.getFirstName()).isEqualTo("Jan");
        assertThat(patient.getLastName()).isEqualTo("Nowak");
    }


    @Test
    @Sql(value = "classpath:patient-controller/init-patients.sql", executionPhase = BEFORE_TEST_METHOD)
    void shouldReturn400WhenThereIsNoPatientWithGivenId() throws Exception {

        //when then
        this.mockMvc.perform(get("/patients/{id}", 10L))
                .andExpect(status().isBadRequest());
    }

    private String createExamplePatient() {
        return """
                {
                     "firstName": "Zbigniew",
                     "lastName": "Kowalski",
                     "addressRequest":
                     {
                        "street": "x",
                        "post-code": "34-123",
                        "city": "Warszawa"
                     }
                }
                """;
    }

    private String createExampleUpdatePatient() {
        return """
                {
                     "firstName": "Tomasz",
                     "lastName": "Testowy",
                     "addressRequest":
                     {
                        "street": "testowa",
                        "postCode": "00-122",
                        "city": "Warszawa2"
                     }
                }
                """;
    }
}
