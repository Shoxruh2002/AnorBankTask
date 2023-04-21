package uz.sh.service.impl;

import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceImpl;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import uz.sh.dto.building.BuildingCreateDTO;
import uz.sh.dto.building.BuildingDTO;
import uz.sh.dto.building.BuildingDetailDTO;
import uz.sh.dto.floor.FloorDTO;
import uz.sh.entity.Building;
import uz.sh.entity.Organization;
import uz.sh.exceptions.BadRequestException;
import uz.sh.exceptions.NotFoundException;
import uz.sh.mapper.BuildingMapper;
import uz.sh.repository.BuildingRepository;
import uz.sh.service.AbstractService;
import uz.sh.service.BuildingService;

import java.util.List;
import java.util.Optional;

/**
 * Author: Shoxruh Bekpulatov
 * Time: 4/20/23 9:32 AM
 **/
@AutoJsonRpcServiceImpl
@Service
public class BuildingServiceImpl extends AbstractService<BuildingRepository, BuildingMapper> implements BuildingService {

    private final FloorServiceImpl floorService;
    private final OrganizationServiceImpl organizationService;

    public BuildingServiceImpl(BuildingRepository repository, BuildingMapper mapper, @Lazy FloorServiceImpl floorService, OrganizationServiceImpl organizationService) {
        super(repository, mapper);
        this.floorService = floorService;
        this.organizationService = organizationService;
    }

    @Override
    public Long buildingCreate(BuildingCreateDTO createDTO) {
        Building building = mapper.fromCreateDTO(createDTO);
        Organization organization = organizationService.getOrganizationById(createDTO.getOrganizationId());
        building.setOrganization(organization);
        Building saved = repository.save(building);
        return saved.getId();
    }

    @Override
    public BuildingDTO buildingDTOGetById(Long id) {
        Building building = getBuildingById(id);
        return mapper.toDTO(building);
    }

    public Building getBuildingById(Long id) {
        Optional<Building> optionalBuilding = repository.findById(id);
        if (optionalBuilding.isEmpty())
            throw new NotFoundException("Building Not found with id : " + id);
        return optionalBuilding.get();
    }


    @Override
    public BuildingDetailDTO buildingDetailDTOGetById(Long id) {
        Building building = getBuildingById(id);
        BuildingDetailDTO detailsDTO = mapper.toDetailsDTO(building);
        List<FloorDTO> floorDTOS = floorService.getFloorDTOsByBuildingId(id);
        detailsDTO.setFloors(floorDTOS);
        return detailsDTO;
    }

    @Override
    public List<BuildingDTO> buildingGetAll() {
        List<Building> buildingList = repository.findAll();
        return mapper.toDTO(buildingList);
    }

    @Override
    public Long buildingDelete(Long id) {
        Optional<Building> optionalBuilding = repository.findById(id);
        if (optionalBuilding.isEmpty())
            throw new BadRequestException("Building Not found with id : " + id);
        repository.delete(optionalBuilding.get());
        return optionalBuilding.get().getId();
    }

    @Override
    public Long buildingUpdate(BuildingDTO updateDTO) {
        Long id = updateDTO.getId();
        Optional<Building> optionalBuilding = repository.findById(id);
        if (optionalBuilding.isEmpty())
            throw new BadRequestException("Building Not found with id : " + id);
        Building building = mapper.fromUpdateDTO(updateDTO, optionalBuilding.get());
        repository.save(building);
        return id;
    }

    public List<BuildingDTO> getBuildingDTOSByOrganizationId(Long organizationId) {
        List<Building> buildings = repository.findAllByOrganization_Id(organizationId);
        return mapper.toDTO(buildings);
    }
}
