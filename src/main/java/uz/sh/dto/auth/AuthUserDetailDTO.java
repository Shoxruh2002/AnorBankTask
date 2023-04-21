package uz.sh.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.sh.dto.complex.ComplexDTO;
import uz.sh.entity.Organization;

import java.util.List;

/**
 * @author Shoxruh Bekpulatov
 * Time : 21/04/23
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthUserDetailDTO {

    private String fullName;

    private String phoneNumber;

    private String position;

    private Integer age;

    private List<ComplexDTO> complexes;

}
