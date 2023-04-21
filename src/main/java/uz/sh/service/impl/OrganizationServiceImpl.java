package uz.sh.service.impl;

import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import uz.sh.contraints.ConstMessages;
import uz.sh.dto.building.BuildingDTO;
import uz.sh.dto.organization.OrganizationCreateDTO;
import uz.sh.dto.organization.OrganizationDTO;
import uz.sh.dto.organization.OrganizationDetailDTO;
import uz.sh.entity.Organization;
import uz.sh.exceptions.NotFoundException;
import uz.sh.mapper.OrganizationMapper;
import uz.sh.repository.OrganizationRepository;
import uz.sh.service.AbstractService;
import uz.sh.service.OrganizationService;

import java.util.List;
import java.util.Optional;

import static uz.sh.utils.AppUtils.toJson;

/**
 * Author: Shoxruh Bekpulatov
 * Time: 4/20/23 9:33 AM
 **/
@Slf4j
@AutoJsonRpcServiceImpl
@Service
public class OrganizationServiceImpl extends AbstractService<OrganizationRepository, OrganizationMapper> implements OrganizationService {

    private final BuildingServiceImpl buildingService;

    public OrganizationServiceImpl(OrganizationRepository repository, @Qualifier("organizationMapperImpl") OrganizationMapper mapper, @Lazy BuildingServiceImpl buildingService) {
        super(repository, mapper);
        this.buildingService = buildingService;
    }

    /**
     * Creates new Organization
     *
     * @param createDTO -> dto from comes from FrontEnd for creating new Organization
     * @return id of new created Organization
     */

    @Override
    public Long createOrganization(OrganizationCreateDTO createDTO) {
        Organization organization = mapper.fromCreateDTO(createDTO);
        Organization saved = repository.save(organization);
        log.info("Organization created with : {}", toJson(saved));
        return saved.getId();
    }

    /**
     * @param id -> id of Organization
     * @return short Info of Organization
     */

    @Override
    public OrganizationDTO getOrganizationDTOById(Long id) {
        Organization organization = this.getOrganizationById(id);
        return mapper.toDTO(organization);
    }

    /**
     * @param id -> id of Organization
     * @return finds Organization from database and returns it if found
     * @throws NotFoundException if not found Organization
     */

    public Organization getOrganizationById(Long id) {
        Optional<Organization> optionalOrganization = repository.findById(id);
        if (optionalOrganization.isPresent())
            return optionalOrganization.get();
        throw new NotFoundException(ConstMessages.ORGANIZATION_NOT_FOUND.formatted(id));
    }

    @Override
    public OrganizationDetailDTO getOrganizationDetailById(Long id) {
        Organization organization = this.getOrganizationById(id);
        OrganizationDetailDTO detailsDTO = mapper.toDetailsDTO(organization);
        List<BuildingDTO> buildingDTOS = buildingService.getBuildingDTOSByOrganizationId(id);
        detailsDTO.setBuildings(buildingDTOS);
        return detailsDTO;
    }

    /**
     * @return all the organizations  in database
     */


    @Override
    public List<OrganizationDTO> getAllOrganization() {
        List<Organization> organizationList = repository.findAll();
        return mapper.toDTO(organizationList);
    }

    /**
     * @param id the id of the Organization to be deleted
     * @return id of deleted Organization
     */

    @Override
    public Long deleteOrganization(Long id) {
        Organization dbOrganization = this.getOrganizationById(id);
        repository.delete(dbOrganization);
        log.info("Organization deleted with : {}", toJson(dbOrganization));
        return dbOrganization.getId();
    }

    /**
     * @param updateDTO dto to be updated
     * @return id of updated Organization
     */

    @Override
    public Long updateOrganization(OrganizationDTO updateDTO) {
        Long id = updateDTO.getId();
        Organization dbOrganization = this.getOrganizationById(id);
        Organization organization = mapper.fromUpdateDTO(updateDTO, dbOrganization);
        repository.save(organization);
        return id;
    }
}
