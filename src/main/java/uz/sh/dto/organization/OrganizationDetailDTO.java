package uz.sh.dto.organization;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.sh.dto.GenericDTO;
import uz.sh.dto.building.BuildingDetailDTO;

import javax.validation.constraints.Size;
import java.util.List;

/**
 * Author: Shoxruh Bekpulatov
 * Time: 4/20/23 4:57 PM
 **/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrganizationDetailDTO extends GenericDTO {

    @Size(min = 10, max = 50)
    private String name;

    @Size(max = 255)
    private String address;

    private List<BuildingDetailDTO> buildings;

}
