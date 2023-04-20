package uz.sh.dto.building;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.sh.dto.BaseDTO;

import javax.validation.constraints.Size;

/**
 * Author: Shoxruh Bekpulatov
 * Time: 4/20/23 4:49 PM
 **/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BuildingCreateDTO implements BaseDTO {

    @Size(min = 10, max = 50)
    private String name;

    private Long organizationId;
}
