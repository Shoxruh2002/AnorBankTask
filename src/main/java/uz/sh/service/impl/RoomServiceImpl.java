package uz.sh.service.impl;

import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceImpl;
import org.springframework.stereotype.Service;
import uz.sh.dto.complex.ComplexDTO;
import uz.sh.dto.item.ItemDTO;
import uz.sh.dto.item.ItemDetailDTO;
import uz.sh.dto.room.RoomCreateDTO;
import uz.sh.dto.room.RoomDTO;
import uz.sh.dto.room.RoomDetailDTO;
import uz.sh.dto.room.RoomUpdateDTO;
import uz.sh.entity.Floor;
import uz.sh.entity.Room;
import uz.sh.exceptions.BadRequestException;
import uz.sh.exceptions.NotFoundException;
import uz.sh.mapper.RoomMapper;
import uz.sh.repository.RoomRepository;
import uz.sh.service.AbstractService;
import uz.sh.service.RoomService;

import java.util.List;
import java.util.Optional;

/**
 * Author: Shoxruh Bekpulatov
 * Time: 4/20/23 9:32 AM
 **/
@AutoJsonRpcServiceImpl
@Service
public class RoomServiceImpl extends AbstractService<RoomRepository, RoomMapper> implements RoomService {

    private final FloorServiceImpl floorService;
    private final ItemServiceImpl itemService;
    private final ComplexServiceImpl complexService;

    public RoomServiceImpl(RoomRepository repository, RoomMapper mapper, FloorServiceImpl floorService, ItemServiceImpl itemService, ComplexServiceImpl complexService) {
        super(repository, mapper);
        this.floorService = floorService;
        this.itemService = itemService;
        this.complexService = complexService;
    }

    @Override
    public Long roomCreate(RoomCreateDTO createDTO) {
        Room room = mapper.fromCreateDTO(createDTO);
        Floor floor = floorService.getFloorById(createDTO.getFloorId());
        room.setFloor(floor);
        Room saved = repository.save(room);
        return saved.getId();
    }

    public Room getRoomById(Long id) {
        Optional<Room> roomOptional = repository.findById(id);
        if (roomOptional.isPresent())
            return roomOptional.get();
        throw new NotFoundException("Room not found with id " + id);
    }

    @Override
    public RoomDTO roomGetById(Long id) {
        Room room = this.getRoomById(id);
        return mapper.toDTO(room);
    }

    @Override
    public RoomDetailDTO roomDetailGetById(Long id) {
        Optional<Room> optionalRoom = repository.findById(id);
        if (optionalRoom.isEmpty())
            throw new NotFoundException("Room Not found with id : " + id);
        Room room = optionalRoom.get();
        RoomDetailDTO detailsDTO = mapper.toDetailsDTO(room);
        List<ItemDTO> items = itemService.getItemsByRoomId(id);
        List<ComplexDTO> complexDTOS = complexService.getByRoomId(id);
        detailsDTO.setComplexes(complexDTOS);
        detailsDTO.setItems(items);
        return detailsDTO;
    }

    @Override
    public List<RoomDTO> roomGetAll() {
        List<Room> roomList = repository.findAll();
        return mapper.toDTO(roomList);
    }

    @Override
    public Long roomDelete(Long id) {
        Optional<Room> optionalRoom = repository.findById(id);
        if (optionalRoom.isEmpty())
            throw new BadRequestException("Room Not found with id : " + id);
        repository.delete(optionalRoom.get());
        return optionalRoom.get().getId();
    }

    @Override
    public Long roomUpdate(RoomUpdateDTO updateDTO) {
        Long id = updateDTO.getId();
        Optional<Room> optionalRoom = repository.findById(id);
        if (optionalRoom.isEmpty())
            throw new BadRequestException("Room Not found with id : " + id);
        Room room = mapper.fromUpdateDTO(updateDTO, optionalRoom.get());
        repository.save(room);
        return id;
    }

    public List<RoomDTO> getAllRoomDTOsByFloorId(Long floorId) {
        List<Room> rooms = repository.findAllByFloor_Id(floorId);
        return mapper.toDTO(rooms);
    }
}
