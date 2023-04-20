package uz.sh.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.sh.dto.GenericDTO;
import uz.sh.enums.Role;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

/**
 * Author: Shoxruh Bekpulatov
 * Time: 4/20/23 11:04 AM
 **/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthUserUpdateDTO extends GenericDTO {

    @Size(max = 100)
    private String fullName;

    @Size(min = 7, max = 15)
    private String phoneNumber;

    @Size(max = 40)
    private String position;

    @Positive
    private Integer age;
}
