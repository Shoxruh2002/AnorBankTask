package uz.sh.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import uz.sh.IntegrationTest;
import uz.sh.dto.organization.OrganizationCreateDTO;
import uz.sh.entity.Organization;
import uz.sh.repository.OrganizationRepository;
import uz.sh.utils.JsonRpcBodyDTO;
import uz.sh.utils.JsonRpcReqDTO;
import uz.sh.utils.OrganizationServiceUtils;

import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author Shoxruh Bekpulatov
 * Time : 22/04/23
 */

@IntegrationTest
@AutoConfigureMockMvc
class OrganizationApiTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private OrganizationRepository organizationRepository;

    @BeforeEach
    void setup() {
        Organization organizationEntity = OrganizationServiceUtils.getOrganizationEntity();
        organizationRepository.save(organizationEntity);
    }

    @AfterEach
    void tearDown() {
        organizationRepository.deleteAll();
    }

    @Test
    void it_shoult_create_organization() throws Exception {
        OrganizationCreateDTO createDTO = new OrganizationCreateDTO();
        createDTO.setAddress("NEW ADDRESS");
        createDTO.setName("NEW NAME");
        JsonRpcReqDTO jsonRpcReqDTO = new JsonRpcReqDTO();
        jsonRpcReqDTO.setParams(new JsonRpcBodyDTO(createDTO));
        jsonRpcReqDTO.setId("1");
        jsonRpcReqDTO.setMethod("organization.create");
        jsonRpcReqDTO.setJsonrpc("2.0");

        mockMvc
                .perform(post("/api/v1/anor/task/organization")
                        .content(objectMapper.writeValueAsString(jsonRpcReqDTO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.jsonrpc").value("2.0"))
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.result").isNumber());
    }

    @Test
    void it_should_get_organization_by_id() throws Exception {
        JsonRpcReqDTO jsonRpcReqDTO = new JsonRpcReqDTO();
        jsonRpcReqDTO.setParams(Map.of("id", 1));
        jsonRpcReqDTO.setId("1");
        jsonRpcReqDTO.setMethod("organization.get.by.id");
        jsonRpcReqDTO.setJsonrpc("2.0");

        mockMvc
                .perform(post("/api/v1/anor/task/organization")
                        .content(objectMapper.writeValueAsString(jsonRpcReqDTO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.jsonrpc").value("2.0"))
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.result.id").value(1))
                .andExpect(jsonPath("$.result.name").value(OrganizationServiceUtils.ORGANIZATION_NAME))
                .andExpect(jsonPath("$.result.address").value(OrganizationServiceUtils.ORGANIZATION_ADDRESS));
    }

    @Test
    void it_should_return_404_error() throws Exception {
        JsonRpcReqDTO jsonRpcReqDTO = new JsonRpcReqDTO();
        jsonRpcReqDTO.setParams(Map.of("id", 2222));
        jsonRpcReqDTO.setId("1");
        jsonRpcReqDTO.setMethod("organization.get.by.id");
        jsonRpcReqDTO.setJsonrpc("2.0");

        mockMvc
                .perform(post("/api/v1/anor/task/organization")
                        .content(objectMapper.writeValueAsString(jsonRpcReqDTO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.jsonrpc").value("2.0"))
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.error.code").value(404))
                .andExpect(jsonPath("$.error.message").value("Organization not found with id : 2222 "));
    }

}
