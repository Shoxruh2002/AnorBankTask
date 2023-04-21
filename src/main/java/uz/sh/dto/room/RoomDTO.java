package uz.sh.dto.room;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.sh.dto.GenericDTO;
import uz.sh.dto.floor.FloorDTO;
import uz.sh.entity.Floor;

/**
 * Author: Shoxruh Bekpulatov
 * Time: 4/20/23 5:42 PM
 **/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoomDTO extends GenericDTO {

    private String roomName;

    private String roomNumber;

}
