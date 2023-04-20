package uz.sh.service.impl;

import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceImpl;
import org.springframework.stereotype.Service;
import uz.sh.dto.auth.AuthUserCreateDTO;
import uz.sh.dto.auth.AuthUserDTO;
import uz.sh.entity.AuthUser;
import uz.sh.exceptions.NotFoundException;
import uz.sh.mapper.AuthUserMapper;
import uz.sh.repository.AuthUserRepository;
import uz.sh.service.AbstractService;
import uz.sh.service.AuthUserService;

import java.util.List;
import java.util.Optional;

/**
 * Author: Shoxruh Bekpulatov
 * Time: 4/20/23 9:31 AM
 **/
@AutoJsonRpcServiceImpl
@Service
public class AuthUserServiceImpl extends AbstractService<AuthUserRepository, AuthUserMapper> implements AuthUserService {

    private final OrganizationServiceImpl organizationService;

    public AuthUserServiceImpl(AuthUserRepository repository, AuthUserMapper mapper, OrganizationServiceImpl organizationService) {
        super(repository, mapper);
        this.organizationService = organizationService;
    }

    @Override
    public Long userCreate(AuthUserCreateDTO createDTO) {
        AuthUser authUser = mapper.fromCreateDTO(createDTO);
        AuthUser saved = repository.save(authUser);
        return saved.getId();
    }

    @Override
    public AuthUserDTO userGetById(Long id) {
        Optional<AuthUser> authUserOptional = repository.findById(id);
        if (authUserOptional.isPresent())
            return mapper.toDTO(authUserOptional.get());
        throw new NotFoundException(404, "Auth User not found with id : " + id);
    }

    @Override
    public List<AuthUserDTO> userGetAll() {
        List<AuthUser> userList = repository.findAll();
        return mapper.toDTO(userList);
    }
}
