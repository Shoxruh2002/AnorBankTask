package uz.sh.service;

import com.googlecode.jsonrpc4j.JsonRpcMethod;
import com.googlecode.jsonrpc4j.JsonRpcParam;
import com.googlecode.jsonrpc4j.JsonRpcService;
import uz.sh.dto.auth.AuthUserCreateDTO;
import uz.sh.dto.auth.AuthUserDTO;
import uz.sh.dto.auth.AuthUserDetailDTO;

import java.util.List;

/**
 * Author: Shoxruh Bekpulatov
 * Time: 4/20/23 9:30 AM
 **/
@JsonRpcService("/api/v1/anor/task/auth")
public interface AuthUserService {

    @JsonRpcMethod(value = "user.create")
    Long createAuthUser(
            @JsonRpcParam(value = "body") AuthUserCreateDTO createDTO
    );

    @JsonRpcMethod(value = "user.get.by.id")
    AuthUserDTO getUserDTOById(
            @JsonRpcParam(value = "id") Long id
    );

    @JsonRpcMethod(value = "user.get.detail.by.id")
    AuthUserDetailDTO getUserDetailById(
            @JsonRpcParam(value = "id") Long id
    );

    @JsonRpcMethod("user.get.all")
    List<AuthUserDTO> getAllUser();

}
