package uz.sh.dto.item;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.sh.dto.GenericDTO;

import java.math.BigDecimal;

/**
 * @author Shoxruh Bekpulatov
 * Time : 21/04/23
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemDTO extends GenericDTO {

    private String itemName;

    private String model;

    private BigDecimal price;

}
