package uz.sh.dto.complex;

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
public class ComplexUpdateDTO extends GenericDTO {

    private String complexName;

    private Long roomId;

    private Long authUserId;

}
