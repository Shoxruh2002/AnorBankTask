package uz.sh.dto.floor;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.sh.dto.GenericDTO;

/**
 * Author: Shoxruh Bekpulatov
 * Time: 4/20/23 5:19 PM
 **/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FloorDTO extends GenericDTO {

    private Integer floorNumber;

    private boolean isDown;
}
