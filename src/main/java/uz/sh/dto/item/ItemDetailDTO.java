package uz.sh.dto.item;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.sh.dto.GenericDTO;
import uz.sh.dto.complex.ComplexDTO;
import uz.sh.dto.room.RoomDTO;

import java.math.BigDecimal;

/**
 * Author: Shoxruh Bekpulatov
 * Time: 4/20/23 5:34 PM
 **/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemDetailDTO extends GenericDTO {

    private String itemName;

    private String model;

    private BigDecimal price;

    private ComplexDTO complex;

    private RoomDTO room;
}
