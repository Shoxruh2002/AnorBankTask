package uz.sh.service.impl;

import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import uz.sh.contraints.ConstMessages;
import uz.sh.dto.floor.FloorCreateDTO;
import uz.sh.dto.floor.FloorDTO;
import uz.sh.dto.floor.FloorDetailDTO;
import uz.sh.dto.room.RoomDTO;
import uz.sh.entity.Building;
import uz.sh.entity.Floor;
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

    public FloorServiceImpl(FloorRepository repository, @Qualifier("floorMapperImpl")  FloorMapper mapper, BuildingServiceImpl buildingService, @Lazy RoomServiceImpl roomService) {
        super(repository, mapper);
        this.buildingService = buildingService;
        this.roomService = roomService;
    }

    /**
     * Creates new Floor
     *
     * @param createDTO -> dto from comes from FrontEnd for creating new Floor
     * @return id of new created Floor
     */

    @Override
    public Long createFloor(FloorCreateDTO createDTO) {
        Floor floor = mapper.fromCreateDTO(createDTO);
        Building building = buildingService.getBuildingById(createDTO.getBuildingId());
        floor.setBuilding(building);
        Floor saved = repository.save(floor);
        return saved.getId();
    }

    /**
     * @param id -> id of Floor
     * @return short Info of Floor
     */

    @Override
    public FloorDTO getFloorDTOById(Long id) {
        Floor floor = this.getFloorById(id);
        return mapper.toDTO(floor);
    }

    /**
     * @param id -> id of Floor
     * @return Full Info of Floor with all rooms
     */


    @Override
    public FloorDetailDTO getFloorDetailById(Long id) {
        Floor floor = this.getFloorById(id);
        FloorDetailDTO detailsDTO = mapper.toDetailsDTO(floor);
        List<RoomDTO> rooms = roomService.getAllRoomDTOsByFloorId(id);
        detailsDTO.setRooms(rooms);
        return detailsDTO;
    }

    /**
     * @return all the floors  in database
     */


    @Override
    public List<FloorDTO> getAllFloor() {
        List<Floor> floorList = repository.findAll();
        return mapper.toDTO(floorList);
    }

    /**
     * @param id the id of the Floor to be deleted
     * @return id of deleted Floor
     */


    @Override
    public Long deleteFloor(Long id) {
        Floor dbFloor = this.getFloorById(id);
        repository.delete(dbFloor);
        return dbFloor.getId();
    }

    /**
     * @param updateDTO dto to be updated
     * @return id of updated Floor
     */

    @Override
    public Long updateFloor(FloorDTO updateDTO) {
        Long id = updateDTO.getId();
        Floor dbFloor = this.getFloorById(id);
        Floor floor = mapper.fromUpdateDTO(updateDTO, dbFloor);
        repository.save(floor);
        return id;
    }

    /**
     * @param id -> id of Floor
     * @return finds Floor from database and returns it if found
     * @throws NotFoundException if not found Floor
     */

    public Floor getFloorById(Long id) {
        Optional<Floor> optionalFloor = repository.findById(id);
        if (optionalFloor.isPresent())
            return optionalFloor.get();
        throw new NotFoundException( ConstMessages.FLOOR_NOT_FOUND.formatted(id));
    }

    /**
     * @param buildingId -> id of the building
     * @return all floors of building
     */
    public List<FloorDTO> getFloorDTOsByBuildingId(Long buildingId) {
        List<Floor> floors = repository.findAllByBuilding_Id(buildingId);
        return mapper.toDTO(floors);
    }
}
