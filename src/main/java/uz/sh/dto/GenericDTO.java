package uz.sh.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * Author: Shoxruh Bekpulatov
 * Time: 4/20/23 10:51 AM
 **/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GenericDTO implements BaseDTO {

    @NotNull
    private Long id;
}
