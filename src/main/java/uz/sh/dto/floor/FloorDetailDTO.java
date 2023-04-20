package uz.sh.dto.floor;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.sh.dto.GenericDTO;
import uz.sh.dto.room.RoomDetailDTO;

import java.util.List;

/**
 * Author: Shoxruh Bekpulatov
 * Time: 4/20/23 5:21 PM
 **/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FloorDetailDTO extends GenericDTO {

    private Integer floorNumber;

    private boolean isDown;

    private List<RoomDetailDTO> rooms;
}
