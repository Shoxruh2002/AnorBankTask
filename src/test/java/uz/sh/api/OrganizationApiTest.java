package uz.sh.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import uz.sh.IntegrationTest;
import uz.sh.dto.building.BuildingCreateDTO;
import uz.sh.dto.organization.OrganizationCreateDTO;
import uz.sh.dto.organization.OrganizationDTO;
import uz.sh.entity.Organization;
import uz.sh.repository.OrganizationRepository;
import uz.sh.service.impl.BuildingServiceImpl;
import uz.sh.utils.JsonRpcBodyDTO;
import uz.sh.utils.JsonRpcReqDTO;
import uz.sh.utils.TestingUtils;

import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author Shoxruh Bekpulatov
 * Time : 22/04/23
 */

@IntegrationTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class OrganizationApiTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private OrganizationRepository organizationRepository;

    @Autowired
    private BuildingServiceImpl buildingService;

    @BeforeAll
    void setup() {
        Organization organizationEntity = TestingUtils.getOrganizationEntity();
        organizationRepository.save(organizationEntity);

        BuildingCreateDTO buildingCreateDTO = new BuildingCreateDTO();
        buildingCreateDTO.setOrganizationId(1L);
        buildingCreateDTO.setName(TestingUtils.BUILDING_NAME);
        buildingService.createBuilding(buildingCreateDTO);
    }

    @AfterAll
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
                .andExpect(jsonPath("$.result.name").value(TestingUtils.ORGANIZATION_NAME))
                .andExpect(jsonPath("$.result.address").value(TestingUtils.ORGANIZATION_ADDRESS));
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

    @Test
    void it_should_return_organization_details() throws Exception {
        JsonRpcReqDTO jsonRpcReqDTO = new JsonRpcReqDTO();
        jsonRpcReqDTO.setParams(Map.of("id", 1));
        jsonRpcReqDTO.setId("1");
        jsonRpcReqDTO.setMethod("organization.get.detail.by.id");
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
                .andExpect(jsonPath("$.result.name").value(TestingUtils.ORGANIZATION_NAME))
                .andExpect(jsonPath("$.result.address").value(TestingUtils.ORGANIZATION_ADDRESS))
                .andExpect(jsonPath("$.result.buildings[0].name").value(TestingUtils.BUILDING_NAME));
    }

    @Test
    void it_shoult_return_all_organizations() throws Exception {
        JsonRpcReqDTO jsonRpcReqDTO = new JsonRpcReqDTO();
        jsonRpcReqDTO.setId("1");
        jsonRpcReqDTO.setMethod("organization.get.all");
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
                .andExpect(jsonPath("$.result[0].id").value(1))
                .andExpect(jsonPath("$.result[0].name").value(TestingUtils.ORGANIZATION_NAME))
                .andExpect(jsonPath("$.result[0].address").value(TestingUtils.ORGANIZATION_ADDRESS));
    }

    @Test
    void it_should_delete_organization() throws Exception {
        Organization saved = organizationRepository.save(
                new Organization("TEST", "TEST ADDRESS", null)
        );

        JsonRpcReqDTO jsonRpcReqDTO = new JsonRpcReqDTO();
        jsonRpcReqDTO.setParams(Map.of("id", saved.getId()));
        jsonRpcReqDTO.setId("1");
        jsonRpcReqDTO.setMethod("organization.delete");
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
                .andExpect(jsonPath("$.result").value(saved.getId()));
    }

    @Test
    void it_should_update_organization() throws Exception {
        Organization saved = organizationRepository.save(
                new Organization("TEST", "TEST ADDRESS", null)
        );

        String updatedName = "UPDATED NAME";
        String updatedAddress = "UPDATED ADDRESS";

        OrganizationDTO updateDTO = new OrganizationDTO();
        updateDTO.setId(saved.getId());
        updateDTO.setName(updatedName);
        updateDTO.setAddress(updatedAddress);

        JsonRpcReqDTO jsonRpcReqDTO = new JsonRpcReqDTO();
        jsonRpcReqDTO.setParams(new JsonRpcBodyDTO(updateDTO));
        jsonRpcReqDTO.setId("1");
        jsonRpcReqDTO.setMethod("organization.update");
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
                .andExpect(jsonPath("$.result").value(saved.getId()));
    }

}
