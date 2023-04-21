package uz.sh.service.impl;

import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import uz.sh.contraints.ConstMessages;
import uz.sh.dto.complex.ComplexDTO;
import uz.sh.dto.item.ItemDTO;
import uz.sh.dto.room.RoomCreateDTO;
import uz.sh.dto.room.RoomDTO;
import uz.sh.dto.room.RoomDetailDTO;
import uz.sh.dto.room.RoomUpdateDTO;
import uz.sh.entity.Floor;
import uz.sh.entity.Room;
import uz.sh.exceptions.NotFoundException;
import uz.sh.mapper.RoomMapper;
import uz.sh.repository.RoomRepository;
import uz.sh.service.AbstractService;
import uz.sh.service.RoomService;

import java.util.List;
import java.util.Optional;

import static uz.sh.utils.AppUtils.toJson;

/**
 * Author: Shoxruh Bekpulatov
 * Time: 4/20/23 9:32 AM
 **/
@Slf4j
@AutoJsonRpcServiceImpl
@Service
public class RoomServiceImpl extends AbstractService<RoomRepository, RoomMapper> implements RoomService {

    private final FloorServiceImpl floorService;
    private final ItemServiceImpl itemService;
    private final ComplexServiceImpl complexService;

    public RoomServiceImpl(RoomRepository repository, @Qualifier("roomMapperImpl") RoomMapper mapper, FloorServiceImpl floorService, ItemServiceImpl itemService, ComplexServiceImpl complexService) {
        super(repository, mapper);
        this.floorService = floorService;
        this.itemService = itemService;
        this.complexService = complexService;
    }

    /**
     * Creates new Room
     *
     * @param createDTO -> dto from comes from FrontEnd for creating new Room
     * @return id of new created Room
     */

    @Override
    public Long createRoom(RoomCreateDTO createDTO) {
        Room room = mapper.fromCreateDTO(createDTO);
        Floor floor = floorService.getFloorById(createDTO.getFloorId());
        room.setFloor(floor);
        Room saved = repository.save(room);
        log.info("Room created with : {}", toJson(saved));
        return saved.getId();
    }

    /**
     * @param id -> id of Room
     * @return short Info of Room
     */

    public Room getRoomById(Long id) {
        Optional<Room> roomOptional = repository.findById(id);
        if (roomOptional.isPresent())
            return roomOptional.get();
        throw new NotFoundException(ConstMessages.ROOM_NOT_FOUND.formatted(id));
    }

    @Override
    public RoomDTO getRoomDTOById(Long id) {
        Room room = this.getRoomById(id);
        return mapper.toDTO(room);
    }

    /**
     * @param id -> id of Room
     * @return Full Info of Room with all items and complexes
     */

    @Override
    public RoomDetailDTO getRoomDetailById(Long id) {
        Room room = this.getRoomById(id);
        RoomDetailDTO detailsDTO = mapper.toDetailsDTO(room);
        List<ItemDTO> items = itemService.getItemsByRoomId(id);
        List<ComplexDTO> complexDTOS = complexService.getByRoomId(id);
        detailsDTO.setComplexes(complexDTOS);
        detailsDTO.setItems(items);
        return detailsDTO;
    }

    /**
     * @return all the rooms  in database
     */


    @Override
    public List<RoomDTO> getAllRoomDTO() {
        List<Room> roomList = repository.findAll();
        return mapper.toDTO(roomList);
    }

    /**
     * @param id the id of the Room to be deleted
     * @return id of deleted Room
     */

    @Override
    public Long deleteRoom(Long id) {
        Room dbRoom = this.getRoomById(id);
        repository.delete(dbRoom);
        log.info("Room deleted with : {}", toJson(dbRoom));
        return dbRoom.getId();
    }

    /**
     * @param updateDTO dto to be updated
     * @return id of updated Room
     */

    @Override
    public Long updateRoom(RoomUpdateDTO updateDTO) {
        Long id = updateDTO.getId();
        Room dbRoom = this.getRoomById(id);
        Room room = mapper.fromUpdateDTO(updateDTO, dbRoom);
        repository.save(room);
        return id;
    }

    /**
     * @param floorId -> id of the floor
     * @return all buildings of floor
     */
    public List<RoomDTO> getAllRoomDTOsByFloorId(Long floorId) {
        List<Room> rooms = repository.findAllByFloor_Id(floorId);
        return mapper.toDTO(rooms);
    }
}
