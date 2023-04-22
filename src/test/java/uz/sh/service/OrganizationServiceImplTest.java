package uz.sh.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import uz.sh.dto.organization.OrganizationCreateDTO;
import uz.sh.dto.organization.OrganizationDTO;
import uz.sh.dto.organization.OrganizationDetailDTO;
import uz.sh.entity.Organization;
import uz.sh.mapper.OrganizationMapper;
import uz.sh.repository.OrganizationRepository;
import uz.sh.service.impl.BuildingServiceImpl;
import uz.sh.service.impl.OrganizationServiceImpl;
import uz.sh.utils.OrganizationServiceUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Shoxruh Bekpulatov
 * Time : 21/04/23
 */

@ExtendWith(MockitoExtension.class)
class OrganizationServiceImplTest {
    @InjectMocks
    private OrganizationServiceImpl organizationService;

    @Mock
    private OrganizationRepository organizationRepository;

    @Mock
    private OrganizationMapper organizationMapper;

    @Mock
    private BuildingServiceImpl buildingService;


    @Test
    void createOrganizationTest() {
        BDDMockito.when(organizationMapper.fromCreateDTO(ArgumentMatchers.any()))
                .thenReturn(OrganizationServiceUtils.getOrganizationEntity());
        BDDMockito.when(organizationRepository.save(ArgumentMatchers.any()))
                .thenReturn(OrganizationServiceUtils.getOrganizationEntity());

        Long organizationId = organizationService.createOrganization(new OrganizationCreateDTO());
        Assertions.assertEquals(1L, organizationId);
    }

    @Test
    void getOrganizationDTOByIdTest() {
        BDDMockito.when(organizationRepository.findById(ArgumentMatchers.any()))
                .thenReturn(Optional.of(OrganizationServiceUtils.getOrganizationEntity()));
        BDDMockito.when(organizationMapper.toDTO(ArgumentMatchers.any(Organization.class)))
                .thenReturn(OrganizationServiceUtils.getOrganizationDTO());

        OrganizationDTO organizationDTO = organizationService.getOrganizationDTOById(1L);
        Assertions.assertEquals(OrganizationServiceUtils.ORGANIZATION_NAME, organizationDTO.getName());
        Assertions.assertEquals(OrganizationServiceUtils.ORGANIZATION_ADDRESS, organizationDTO.getAddress());
    }

    @Test
    void getOrganizationByIdTest() {
        BDDMockito.when(organizationRepository.findById(ArgumentMatchers.any()))
                .thenReturn(Optional.of(OrganizationServiceUtils.getOrganizationEntity()));

        Organization organization = organizationService.getOrganizationById(1L);
        Assertions.assertEquals(OrganizationServiceUtils.ORGANIZATION_NAME, organization.getName());
        Assertions.assertEquals(OrganizationServiceUtils.ORGANIZATION_ADDRESS, organization.getAddress());
    }

    @Test
    void getOrganizationDetailByIdTest() {
        BDDMockito.when(organizationRepository.findById(ArgumentMatchers.any()))
                .thenReturn(Optional.of(OrganizationServiceUtils.getOrganizationEntity()));
        BDDMockito.when(organizationMapper.toDetailsDTO(ArgumentMatchers.any()))
                .thenReturn(OrganizationServiceUtils.getOrganizationDetailDTO());
        BDDMockito.when(buildingService.getBuildingDTOSByOrganizationId(ArgumentMatchers.any()))
                .thenReturn(new ArrayList<>());

        OrganizationDetailDTO organizationDetails = organizationService.getOrganizationDetailById(1L);
        Assertions.assertEquals(OrganizationServiceUtils.ORGANIZATION_NAME, organizationDetails.getName());
        Assertions.assertEquals(OrganizationServiceUtils.ORGANIZATION_ADDRESS, organizationDetails.getAddress());
    }

    @Test
    void getAllOrganizationTest() {
        BDDMockito.when(organizationRepository.findAll())
                .thenReturn(List.of(OrganizationServiceUtils.getOrganizationEntity()));
        BDDMockito.when(organizationMapper.toDTO(ArgumentMatchers.anyList()))
                .thenReturn(List.of(OrganizationServiceUtils.getOrganizationDTO()));

        List<OrganizationDTO> allOrganization = organizationService.getAllOrganization();
        Assertions.assertEquals(1, allOrganization.size());
        Assertions.assertEquals(OrganizationServiceUtils.ORGANIZATION_NAME, allOrganization.get(0).getName());
    }

    @Test
    void deleteOrganizationTest() {
        Organization organization = OrganizationServiceUtils.getOrganizationEntity();
        BDDMockito.when(organizationRepository.findById(ArgumentMatchers.any()))
                .thenReturn(Optional.of(organization));
        BDDMockito.doNothing().when(organizationRepository).delete(organization);

        Long id = organizationService.deleteOrganization(1L);
        Assertions.assertEquals(1L, id);
    }

    @Test
    void updateOrganizationTest() {
        Organization organization = OrganizationServiceUtils.getOrganizationEntity();
        BDDMockito.when(organizationRepository.findById(ArgumentMatchers.any()))
                .thenReturn(Optional.of(organization));
        BDDMockito.when(organizationMapper.fromUpdateDTO(ArgumentMatchers.any(), ArgumentMatchers.any()))
                .thenReturn(organization);
        BDDMockito.when(organizationRepository.save(ArgumentMatchers.any()))
                .thenReturn(organization);

        OrganizationDTO organizationDTO = OrganizationServiceUtils.getOrganizationDTO();
        organizationDTO.setId(1L);
        Long id = organizationService.updateOrganization(organizationDTO);
        Assertions.assertEquals(1L, id);
    }

}
