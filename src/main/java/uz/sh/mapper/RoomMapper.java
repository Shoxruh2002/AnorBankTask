package uz.sh.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;
import uz.sh.dto.room.RoomCreateDTO;
import uz.sh.dto.room.RoomDTO;
import uz.sh.dto.room.RoomDetailDTO;
import uz.sh.dto.room.RoomUpdateDTO;
import uz.sh.entity.Room;

import java.util.List;

/**
 * Author: Shoxruh Bekpulatov
 * Time: 4/20/23 5:43 PM
 **/
@Component
@Mapper(componentModel = "spring")
public interface RoomMapper extends BaseMapper {

    Room fromCreateDTO(RoomCreateDTO createDTO);

    RoomDTO toDTO(Room rooms);

    RoomDetailDTO toDetailsDTO(Room rooms);

    List<RoomDTO> toDTO(List<Room> rooms);

    Room fromUpdateDTO(RoomUpdateDTO updateDTO, @MappingTarget Room room);
}
