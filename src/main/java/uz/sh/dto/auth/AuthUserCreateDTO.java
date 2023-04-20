package uz.sh.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.sh.dto.BaseDTO;
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
public class AuthUserCreateDTO implements BaseDTO {

    @NotNull
    @Size(min = 6, max = 50)
    private String username;

    @NotNull
    @Size(min = 6, max = 15)
    private String password;

    @NotNull
    private Role role;

    @Size(max = 100)
    private String fullName;

    @Size(min = 7, max = 15)
    private String phoneNumber;

    @Size(max = 40)
    private String position;

    @Positive
    private Integer age;
}
