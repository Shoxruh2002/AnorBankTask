package uz.sh.dto.complex;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.sh.dto.GenericDTO;
import uz.sh.dto.auth.AuthUserDTO;
import uz.sh.dto.room.RoomDTO;
import uz.sh.entity.AuthUser;
import uz.sh.entity.Room;

/**
 * Author: Shoxruh Bekpulatov
 * Time: 4/20/23 5:38 PM
 **/
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ComplexDTO extends GenericDTO {

    private String complexName;

    private RoomDTO room;

    private AuthUserDTO authUser;

}
