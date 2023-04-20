package uz.sh.service.impl;

import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceImpl;
import org.mapstruct.Qualifier;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.sh.dto.auth.AuthUserCreateDTO;
import uz.sh.dto.auth.AuthUserDTO;
import uz.sh.dto.auth.AuthUserUpdateDTO;
import uz.sh.entity.AuthUser;
import uz.sh.enums.Role;
import uz.sh.exceptions.NotFoundException;
import uz.sh.mapper.AuthUserMapper;
import uz.sh.repository.AuthUserRepository;
import uz.sh.service.AbstractService;
import uz.sh.service.AuthUserService;
import uz.sh.service.GenericService;

import java.util.List;
import java.util.Optional;

/**
 * Author: Shoxruh Bekpulatov
 * Time: 4/20/23 9:31 AM
 **/
@AutoJsonRpcServiceImpl
@Service
public class AuthUserServiceImpl extends AbstractService<AuthUserRepository, AuthUserMapper> implements AuthUserService,
        GenericService<AuthUserCreateDTO, AuthUserUpdateDTO, AuthUserDTO, Long>{

    public AuthUserServiceImpl(AuthUserRepository repository, AuthUserMapper mapper) {
        super(repository, mapper);
//        this.create(new AuthUserCreateDTO("Shoxruh0912", "Shoxruh0912", Role.ADMIN, "Shoxruh Bekpulatov", "+998945285979", "Java Mid dev", 20));
    }

    @Override
    public Long userCreateRequest(AuthUserCreateDTO createDTO) {
        return this.create(createDTO);
    }

    @Override
    public List<AuthUserDTO> userGetAllRequest() {
        List<AuthUser> userList = repository.findAll();
        return mapper.toDTO(userList);
    }

    @Override
    public Long create(AuthUserCreateDTO createDTO) {
        AuthUser authUser = mapper.fromCreateDTO(createDTO);
        AuthUser saved = repository.save(authUser);
        return saved.getId();
    }

    @Override
    public Long delete(Long id) {
        return null;
    }

    @Override
    public Long update(AuthUserUpdateDTO updateDTO) {
        return null;
    }

    @Override
    public AuthUserDTO getById(Long id) {
        Optional<AuthUser> authUserOptional = repository.findById(id);
        if (authUserOptional.isPresent())
            return mapper.toDTO(authUserOptional.get());
        throw new NotFoundException("Auth User not found with id : " + id);
    }

}
