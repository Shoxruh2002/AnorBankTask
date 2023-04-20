package uz.sh.service.impl;

import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceImpl;
import org.springframework.stereotype.Service;
import uz.sh.dto.building.BuildingCreateDTO;
import uz.sh.dto.building.BuildingDTO;
import uz.sh.dto.building.BuildingDetailDTO;
import uz.sh.entity.Building;
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

    public BuildingServiceImpl(BuildingRepository repository, BuildingMapper mapper) {
        super(repository, mapper);
    }

    @Override
    public Long buildingCreate(BuildingCreateDTO createDTO) {
        Building building = mapper.fromCreateDTO(createDTO);
        Building saved = repository.save(building);
        return saved.getId();
    }

    @Override
    public BuildingDTO buildingGetById(Long id) {
        Optional<Building> optionalBuilding = repository.findById(id);
        if (optionalBuilding.isEmpty())
            throw new NotFoundException("Building Not found with id : " + id);
        Building building = optionalBuilding.get();
        return mapper.toDTO(building);
    }

    @Override
    public BuildingDetailDTO buildingDetailGetById(Long id) {
        Optional<Building> optionalBuilding = repository.findById(id);
        if (optionalBuilding.isEmpty())
            throw new NotFoundException("Building Not found with id : " + id);
        Building building = optionalBuilding.get();
        BuildingDetailDTO detailsDTO = mapper.toDetailsDTO(building);
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
}
