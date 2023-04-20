package uz.sh.dto.building;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.sh.dto.GenericDTO;

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
public class BuildingDTO extends GenericDTO {

    private String name;

}
