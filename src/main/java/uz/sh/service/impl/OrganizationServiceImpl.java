package uz.sh.service.impl;

import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceImpl;
import org.springframework.stereotype.Service;
import uz.sh.dto.organization.OrganizationCreateDTO;
import uz.sh.dto.organization.OrganizationDTO;
import uz.sh.dto.organization.OrganizationDetailDTO;
import uz.sh.entity.Organization;
import uz.sh.exceptions.BadRequestException;
import uz.sh.exceptions.NotFoundException;
import uz.sh.mapper.OrganizationMapper;
import uz.sh.repository.OrganizationRepository;
import uz.sh.service.AbstractService;
import uz.sh.service.OrganizationService;

import java.util.List;
import java.util.Optional;

/**
 * Author: Shoxruh Bekpulatov
 * Time: 4/20/23 9:33 AM
 **/
@AutoJsonRpcServiceImpl
@Service
public class OrganizationServiceImpl extends AbstractService<OrganizationRepository, OrganizationMapper> implements OrganizationService {

    private final BuildingServiceImpl buildingService;

    public OrganizationServiceImpl(OrganizationRepository repository, OrganizationMapper mapper, BuildingServiceImpl buildingService) {
        super(repository, mapper);
        this.buildingService = buildingService;
    }

    @Override
    public Long organizationCreate(OrganizationCreateDTO createDTO) {
        Organization organization = mapper.fromCreateDTO(createDTO);
        Organization saved = repository.save(organization);
        return saved.getId();
    }

    @Override
    public OrganizationDTO organizationGetById(Long id) {
        Optional<Organization> optionalOrganization = repository.findById(id);
        if (optionalOrganization.isEmpty())
            throw new NotFoundException("Organization Not found with id : " + id);
        Organization organization = optionalOrganization.get();
        return mapper.toDTO(organization);
    }

    @Override
    public OrganizationDetailDTO organizationDetailGetById(Long id) {
        Optional<Organization> optionalOrganization = repository.findById(id);
        if (optionalOrganization.isEmpty())
            throw new NotFoundException("Organization Not found with id : " + id);
        Organization organization = optionalOrganization.get();
        OrganizationDetailDTO detailsDTO = mapper.toDetailsDTO(organization);
        return detailsDTO;
    }

    @Override
    public List<OrganizationDTO> organizationGetAll() {
        List<Organization> organizationList = repository.findAll();
        return mapper.toDTO(organizationList);
    }

    @Override
    public Long organizationDelete(Long id) {
        Optional<Organization> optionalOrganization = repository.findById(id);
        if (optionalOrganization.isEmpty())
            throw new BadRequestException("Organization Not found with id : " + id);
        repository.delete(optionalOrganization.get());
        return optionalOrganization.get().getId();
    }

    @Override
    public Long organizationUpdate(OrganizationDTO updateDTO) {
        Long id = updateDTO.getId();
        Optional<Organization> optionalOrganization = repository.findById(id);
        if (optionalOrganization.isEmpty())
            throw new BadRequestException("Organization Not found with id : " + id);
        Organization organization = mapper.fromUpdateDTO(updateDTO, optionalOrganization.get());
        repository.save(organization);
        return id;
    }
}
