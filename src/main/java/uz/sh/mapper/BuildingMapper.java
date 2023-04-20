package uz.sh.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;
import uz.sh.dto.building.BuildingCreateDTO;
import uz.sh.dto.building.BuildingDTO;
import uz.sh.dto.building.BuildingDetailDTO;
import uz.sh.entity.Building;

import java.util.List;

/**
 * Author: Shoxruh Bekpulatov
 * Time: 4/20/23 5:48 PM
 **/
@Component
@Mapper(componentModel = "spring")
public interface BuildingMapper extends BaseMapper {


    Building fromCreateDTO(BuildingCreateDTO createDTO);

    BuildingDTO toDTO(Building buildings);

    BuildingDetailDTO toDetailsDTO(Building buildings);

    List<BuildingDTO> toDTO(List<Building> buildings);

    Building fromUpdateDTO(BuildingDTO updateDTO, @MappingTarget Building building);
}
