package uz.sh.service.impl;

import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceImpl;
import org.springframework.stereotype.Service;
import uz.sh.dto.room.RoomCreateDTO;
import uz.sh.dto.room.RoomDTO;
import uz.sh.dto.room.RoomDetailDTO;
import uz.sh.entity.Room;
import uz.sh.exceptions.BadRequestException;
import uz.sh.exceptions.NotFoundException;
import uz.sh.mapper.RoomMapper;
import uz.sh.repository.RoomRepository;
import uz.sh.service.AbstractService;
import uz.sh.service.RoomService;
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

    private final BuildingServiceImpl buildingService;

    public RoomServiceImpl(RoomRepository repository, RoomMapper mapper, BuildingServiceImpl buildingService) {
        super(repository, mapper);
        this.buildingService = buildingService;
    }

    @Override
    public Long roomCreate(RoomCreateDTO createDTO) {
        Room room = mapper.fromCreateDTO(createDTO);
        Room saved = repository.save(room);
        return saved.getId();
    }

    @Override
    public RoomDTO roomGetById(Long id) {
        Optional<Room> optionalRoom = repository.findById(id);
        if (optionalRoom.isEmpty())
            throw new NotFoundException("Room Not found with id : " + id);
        Room room = optionalRoom.get();
        return mapper.toDTO(room);
    }

    @Override
    public RoomDetailDTO roomDetailGetById(Long id) {
        Optional<Room> optionalRoom = repository.findById(id);
        if (optionalRoom.isEmpty())
            throw new NotFoundException("Room Not found with id : " + id);
        Room room = optionalRoom.get();
        RoomDetailDTO detailsDTO = mapper.toDetailsDTO(room);
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
    public Long roomUpdate(RoomDTO updateDTO) {
        Long id = updateDTO.getId();
        Optional<Room> optionalRoom = repository.findById(id);
        if (optionalRoom.isEmpty())
            throw new BadRequestException("Room Not found with id : " + id);
        Room room = mapper.fromUpdateDTO(updateDTO, optionalRoom.get());
        repository.save(room);
        return id;
    }
}
