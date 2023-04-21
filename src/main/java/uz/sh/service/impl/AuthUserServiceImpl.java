package uz.sh.service.impl;

import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import uz.sh.contraints.ConstMessages;
import uz.sh.dto.auth.AuthUserCreateDTO;
import uz.sh.dto.auth.AuthUserDTO;
import uz.sh.dto.auth.AuthUserDetailDTO;
import uz.sh.dto.complex.ComplexDTO;
import uz.sh.entity.AuthUser;
import uz.sh.exceptions.NotFoundException;
import uz.sh.mapper.AuthUserMapper;
import uz.sh.repository.AuthUserRepository;
import uz.sh.service.AbstractService;
import uz.sh.service.AuthUserService;

import java.util.List;
import java.util.Optional;

import static uz.sh.utils.AppUtils.toJson;

/**
 * Author: Shoxruh Bekpulatov
 * Time: 4/20/23 9:31 AM
 **/
@AutoJsonRpcServiceImpl
@Service
@Slf4j
public class AuthUserServiceImpl extends AbstractService<AuthUserRepository, AuthUserMapper> implements AuthUserService {

    private final ComplexServiceImpl complexService;

    public AuthUserServiceImpl(AuthUserRepository repository, @Qualifier("authUserMapperImpl") AuthUserMapper mapper, ComplexServiceImpl complexService) {
        super(repository, mapper);
        this.complexService = complexService;
    }

    /**
     * Creates new AuthUser
     *
     * @param createDTO -> dto from comes from FrontEnd for creating new AuthUser
     * @return id of new created AuthUser
     */
    @Override
    public Long createAuthUser(AuthUserCreateDTO createDTO) {
        AuthUser authUser = mapper.fromCreateDTO(createDTO);
        AuthUser saved = repository.save(authUser);
        log.info("AuthUser created with : {} ", toJson(saved));
        return saved.getId();
    }

    /**
     * @param id -> id of AuthUser
     * @return short Info of AuthUser
     */

    @Override
    public AuthUserDTO getUserDTOById(Long id) {
        AuthUser authUser = this.getAuthUserById(id);
        return mapper.toDTO(authUser);
    }

    /**
     * @param id -> id of AuthUser
     * @return Full Info of AuthUser with all floors
     */

    @Override
    public AuthUserDetailDTO getUserDetailById(Long id) {
        AuthUser authUser = this.getAuthUserById(id);
        AuthUserDetailDTO detailDTO = mapper.toDetailDTO(authUser);
        List<ComplexDTO> complexDTOList = complexService.getByAuthUserId(id);
        detailDTO.setComplexes(complexDTOList);
        return detailDTO;
    }

    /**
     * @return all the auth users  in database
     */

    @Override
    public List<AuthUserDTO> getAllUser() {
        List<AuthUser> userList = repository.findAll();
        return mapper.toDTO(userList);
    }

    /**
     * @param id -> id of AuthUser
     * @return finds AuthUser from database and returns it if found
     * @throws NotFoundException if not found AuthUser
     */


    public AuthUser getAuthUserById(Long id) {
        Optional<AuthUser> authUserOptional = repository.findById(id);
        if (authUserOptional.isPresent())
            return authUserOptional.get();
        throw new NotFoundException(404, ConstMessages.AUTH_USER_NOT_FOUND.formatted(id));
    }
}
