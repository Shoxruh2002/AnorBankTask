package uz.sh.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.sh.dto.BaseDTO;

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
public class AuthUserCreateDTO implements BaseDTO {

    private String fullName;

    private String phoneNumber;

    private String position;

    private Integer age;
}
