package uz.sh.dto.building;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.sh.dto.GenericDTO;
import uz.sh.dto.floor.FloorDTO;

import java.util.List;

/**
 * Author: Shoxruh Bekpulatov
 * Time: 4/20/23 5:12 PM
 **/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BuildingDetailDTO extends GenericDTO {

    private String name;

    private List<FloorDTO> floors;
}
