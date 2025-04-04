import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microserfices.project.models.Patient;
import com.microserfices.project.repository.PatientRepository;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.time.LocalDate;
import java.util.Collections;

@SpringBootTest
@AutoConfigureMockMvc
class PatientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
        patientRepository.deleteAll(); // Clears DB before each test
    }

    @Test
    void shouldCreatePatient() throws Exception {
        
        Patient patient = new Patient(
    null,               
    "John Doe",         
    "john@example.com", 
    LocalDate.of(1994, 5, 21), 
    "Male",           
    "123 Main St",     
    "123-456-7890",   
    "O+",             
    Collections.emptyList(),
    Collections.emptyList(),
    Collections.emptyList() 
);

        // Convert the patient object to JSON
        String patientJson = objectMapper.writeValueAsString(patient);

        // Perform the POST request to create a new patient
        mockMvc.perform(MockMvcRequestBuilders.post("/api/patients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(patientJson))
                .andExpect(status().isOk());  // Your controller returns 200 OK, not 201 CREATED
    }
}
