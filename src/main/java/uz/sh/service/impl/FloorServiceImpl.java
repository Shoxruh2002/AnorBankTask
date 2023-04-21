package uz.sh.service.impl;

import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceImpl;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import uz.sh.dto.floor.FloorCreateDTO;
import uz.sh.dto.floor.FloorDTO;
import uz.sh.dto.floor.FloorDetailDTO;
import uz.sh.dto.room.RoomDTO;
import uz.sh.entity.Building;
import uz.sh.entity.Floor;
import uz.sh.exceptions.BadRequestException;
import uz.sh.exceptions.NotFoundException;
import uz.sh.mapper.FloorMapper;
import uz.sh.repository.FloorRepository;
import uz.sh.service.AbstractService;
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
    private final RoomServiceImpl roomService;

    public FloorServiceImpl(FloorRepository repository, FloorMapper mapper, BuildingServiceImpl buildingService, @Lazy RoomServiceImpl roomService) {
        super(repository, mapper);
        this.buildingService = buildingService;
        this.roomService = roomService;
    }

    @Override
    public Long floorCreate(FloorCreateDTO createDTO) {
        Floor floor = mapper.fromCreateDTO(createDTO);
        Building building = buildingService.getBuildingById(createDTO.getBuildingId());
        floor.setBuilding(building);
        Floor saved = repository.save(floor);
        return saved.getId();
    }

    @Override
    public FloorDTO floorGetById(Long id) {
        Floor floor = this.getFloorById(id);
        return mapper.toDTO(floor);
    }

    @Override
    public FloorDetailDTO floorDetailGetById(Long id) {
        Floor floor = this.getFloorById(id);
        FloorDetailDTO detailsDTO = mapper.toDetailsDTO(floor);
        List<RoomDTO> rooms = roomService.getAllRoomDTOsByFloorId(id);
        detailsDTO.setRooms(rooms);
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

    public Floor getFloorById(Long id) {
        Optional<Floor> optionalFloor = repository.findById(id);
        if (optionalFloor.isPresent())
            return optionalFloor.get();
        throw new NotFoundException("Floor not found with id " + id);
    }

    public List<FloorDTO> getFloorDTOsByBuildingId(Long buildingId) {
        List<Floor> floors = repository.findAllByBuilding_Id(buildingId);
        return mapper.toDTO(floors);
    }
}
