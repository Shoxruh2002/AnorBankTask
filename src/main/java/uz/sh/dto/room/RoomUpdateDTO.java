package uz.sh.dto.room;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.sh.dto.GenericDTO;

/**
 * @author Shoxruh Bekpulatov
 * Time : 21/04/23
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoomUpdateDTO extends GenericDTO {

    private String roomName;

    private String roomNumber;
}
