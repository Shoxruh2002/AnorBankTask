package uz.sh.dto.item;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.sh.dto.BaseDTO;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

/**
 * Author: Shoxruh Bekpulatov
 * Time: 4/20/23 5:35 PM
 **/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemCreateDTO implements BaseDTO {

    private String itemName;

    private String model;

    private BigDecimal price;

    private Long complexId;

    private Long roomId;
}
