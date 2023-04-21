package uz.sh.dto.complex;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.sh.dto.GenericDTO;
import uz.sh.dto.auth.AuthUserDTO;
import uz.sh.dto.item.ItemDTO;
import uz.sh.dto.item.ItemDetailDTO;
import uz.sh.dto.room.RoomDTO;

import java.util.List;

/**
 * Author: Shoxruh Bekpulatov
 * Time: 4/20/23 5:38 PM
 **/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ComplexDetailDTO extends GenericDTO {

    private String complexName;

    private RoomDTO room;

    private AuthUserDTO authUser;

    private List<ItemDTO> items;
}
