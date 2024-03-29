package uz.sh.dto.room;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.sh.dto.BaseDTO;

import javax.validation.constraints.Size;

/**
 * Author: Shoxruh Bekpulatov
 * Time: 4/20/23 5:42 PM
 **/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoomCreateDTO implements BaseDTO {

    private String roomName;

    private String roomNumber;

    private Long floorId;

}
