package uz.sh.service.impl;

import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceImpl;
import org.springframework.stereotype.Service;
import uz.sh.dto.floor.FloorCreateDTO;
import uz.sh.dto.floor.FloorDTO;
import uz.sh.dto.floor.FloorDetailDTO;
import uz.sh.entity.Floor;
import uz.sh.exceptions.BadRequestException;
import uz.sh.exceptions.NotFoundException;
import uz.sh.mapper.FloorMapper;
import uz.sh.repository.FloorRepository;
import uz.sh.service.AbstractService;
import uz.sh.service.FloorService;
import uz.sh.service.FloorService;

import java.util.List;
import java.util.Optional;

/**
 * Author: Shoxruh Bekpulatov
 * Time: 4/20/23 9:33 AM
 **/
@AutoJsonRpcServiceImpl
@Service
public class FloorServiceImpl extends AbstractService<FloorRepository, FloorMapper> implements FloorService {

    private final BuildingServiceImpl buildingService;

    public FloorServiceImpl(FloorRepository repository, FloorMapper mapper, BuildingServiceImpl buildingService) {
        super(repository, mapper);
        this.buildingService = buildingService;
    }

    @Override
    public Long floorCreate(FloorCreateDTO createDTO) {
        Floor floor = mapper.fromCreateDTO(createDTO);
        Floor saved = repository.save(floor);
        return saved.getId();
    }

    @Override
    public FloorDTO floorGetById(Long id) {
        Optional<Floor> optionalFloor = repository.findById(id);
        if (optionalFloor.isEmpty())
            throw new NotFoundException("Floor Not found with id : " + id);
        Floor floor = optionalFloor.get();
        return mapper.toDTO(floor);
    }

    @Override
    public FloorDetailDTO floorDetailGetById(Long id) {
        Optional<Floor> optionalFloor = repository.findById(id);
        if (optionalFloor.isEmpty())
            throw new NotFoundException("Floor Not found with id : " + id);
        Floor floor = optionalFloor.get();
        FloorDetailDTO detailsDTO = mapper.toDetailsDTO(floor);
        return detailsDTO;
    }

    @Override
    public List<FloorDTO> floorGetAll() {
        List<Floor> floorList = repository.findAll();
        return mapper.toDTO(floorList);
    }

    @Override
    public Long floorDelete(Long id) {
        Optional<Floor> optionalFloor = repository.findById(id);
        if (optionalFloor.isEmpty())
            throw new BadRequestException("Floor Not found with id : " + id);
        repository.delete(optionalFloor.get());
        return optionalFloor.get().getId();
    }

    @Override
    public Long floorUpdate(FloorDTO updateDTO) {
        Long id = updateDTO.getId();
        Optional<Floor> optionalFloor = repository.findById(id);
        if (optionalFloor.isEmpty())
            throw new BadRequestException("Floor Not found with id : " + id);
        Floor floor = mapper.fromUpdateDTO(updateDTO, optionalFloor.get());
        repository.save(floor);
        return id;
    }
}
