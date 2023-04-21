package uz.sh.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import uz.sh.dto.auth.AuthUserCreateDTO;
import uz.sh.dto.auth.AuthUserDTO;
import uz.sh.dto.auth.AuthUserDetailDTO;
import uz.sh.entity.AuthUser;

import java.util.List;

/**
 * Author: Shoxruh Bekpulatov
 * Time: 4/20/23 12:23 PM
 **/
@Component
@Mapper(componentModel = "spring")
public interface AuthUserMapper extends BaseMapper {

    AuthUser fromCreateDTO(AuthUserCreateDTO createDTO);

    AuthUserDTO toDTO(AuthUser authUser);

    AuthUserDetailDTO toDetailDTO(AuthUser authUser);

    List<AuthUserDTO> toDTO(List<AuthUser> authUser);
}
