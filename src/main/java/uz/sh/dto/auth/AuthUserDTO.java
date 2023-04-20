package uz.sh.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.sh.dto.GenericDTO;

/**
 * Author: Shoxruh Bekpulatov
 * Time: 4/20/23 11:03 AM
 **/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthUserDTO extends GenericDTO {

    private String fullName;

    private String phoneNumber;

    private String position;

    private Integer age;
}
