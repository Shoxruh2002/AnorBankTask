package uz.sh.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;
import uz.sh.dto.floor.FloorCreateDTO;
import uz.sh.dto.floor.FloorDTO;
import uz.sh.dto.floor.FloorDetailDTO;
import uz.sh.entity.Floor;

import java.util.List;

/**
 * Author: Shoxruh Bekpulatov
 * Time: 4/20/23 5:45 PM
 **/
@Component
@Mapper(componentModel = "spring")
public interface FloorMapper extends BaseMapper {

    Floor fromCreateDTO(FloorCreateDTO createDTO);

    FloorDTO toDTO(Floor floors);

    FloorDetailDTO toDetailsDTO(Floor floors);

    List<FloorDTO> toDTO(List<Floor> floors);

    Floor fromUpdateDTO(FloorDTO updateDTO, @MappingTarget Floor floor);
}
