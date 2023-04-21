package uz.sh.service.impl;

import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import uz.sh.contraints.ConstMessages;
import uz.sh.dto.building.BuildingCreateDTO;
import uz.sh.dto.building.BuildingDTO;
import uz.sh.dto.building.BuildingDetailDTO;
import uz.sh.dto.floor.FloorDTO;
import uz.sh.entity.Building;
import uz.sh.entity.Organization;
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

    public BuildingServiceImpl(BuildingRepository repository, @Qualifier("buildingMapperImpl") BuildingMapper mapper, @Lazy FloorServiceImpl floorService, OrganizationServiceImpl organizationService) {
        super(repository, mapper);
        this.floorService = floorService;
        this.organizationService = organizationService;
    }

    /**
     * Creates new Building
     *
     * @param createDTO -> dto from comes from FrontEnd for creating new Building
     * @return id of new created Building
     */

    @Override
    public Long createBuilding(BuildingCreateDTO createDTO) {
        Building building = mapper.fromCreateDTO(createDTO);
        Organization organization = organizationService.getOrganizationById(createDTO.getOrganizationId());
        building.setOrganization(organization);
        Building saved = repository.save(building);
        return saved.getId();
    }

    /**
     * @param id -> id of Building
     * @return short Info of Building
     */
    @Override
    public BuildingDTO getBuildingDTOById(Long id) {
        Building building = getBuildingById(id);
        return mapper.toDTO(building);
    }

    /**
     * @param id -> id of Building
     * @return finds Building from database and returns it if found
     * @throws NotFoundException if not found Building
     */

    public Building getBuildingById(Long id) {
        Optional<Building> optionalBuilding = repository.findById(id);
        if (optionalBuilding.isEmpty())
            throw new NotFoundException(ConstMessages.BUILDING_NOT_FOUND.formatted(id));
        return optionalBuilding.get();
    }

    /**
     * @param id -> id of Building
     * @return Full Info of Building with all floors
     */

    @Override
    public BuildingDetailDTO getBuildingDetailById(Long id) {
        Building building = getBuildingById(id);
        BuildingDetailDTO detailsDTO = mapper.toDetailsDTO(building);
        List<FloorDTO> floorDTOS = floorService.getFloorDTOsByBuildingId(id);
        detailsDTO.setFloors(floorDTOS);
        return detailsDTO;
    }

    /**
     * @return all the buildings  in database
     */

    @Override
    public List<BuildingDTO> getAllBuilding() {
        List<Building> buildingList = repository.findAll();
        return mapper.toDTO(buildingList);
    }

    /**
     * @param id the id of the Building to be deleted
     * @return id of deleted Building
     */

    @Override
    public Long deleteBuilding(Long id) {
        Building dbBuilding = this.getBuildingById(id);
        repository.delete(dbBuilding);
        return dbBuilding.getId();
    }

    /**
     * @param updateDTO dto to be updated
     * @return id of updated Building
     */

    @Override
    public Long updateBuilding(BuildingDTO updateDTO) {
        Long id = updateDTO.getId();
        Building dbBuilding = this.getBuildingById(id);
        Building building = mapper.fromUpdateDTO(updateDTO, dbBuilding);
        repository.save(building);
        return id;
    }

    /**
     * @param organizationId -> id of the organization
     * @return all buildings of organization
     */
    public List<BuildingDTO> getBuildingDTOSByOrganizationId(Long organizationId) {
        List<Building> buildings = repository.findAllByOrganization_Id(organizationId);
        return mapper.toDTO(buildings);
    }
}
