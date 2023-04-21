package uz.sh.dto.room;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.sh.dto.GenericDTO;
import uz.sh.dto.complex.ComplexDTO;
import uz.sh.dto.floor.FloorDTO;
import uz.sh.dto.item.ItemDTO;
import uz.sh.dto.item.ItemDetailDTO;

import java.util.List;

/**
 * Author: Shoxruh Bekpulatov
 * Time: 4/20/23 5:23 PM
 **/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoomDetailDTO extends GenericDTO {

    private String roomName;

    private String roomNumber;

    private FloorDTO floor;

    private List<ComplexDTO> complexes;

    private List<ItemDTO> items;
}
