package uz.sh.service;

import com.googlecode.jsonrpc4j.JsonRpcMethod;
import com.googlecode.jsonrpc4j.JsonRpcParam;
import com.googlecode.jsonrpc4j.JsonRpcService;
import uz.sh.dto.auth.AuthUserCreateDTO;
import uz.sh.dto.auth.AuthUserDTO;
import uz.sh.entity.AuthUser;

import java.util.List;

/**
 * Author: Shoxruh Bekpulatov
 * Time: 4/20/23 9:30 AM
 **/
@JsonRpcService("/api/v1/anor/task")
public interface AuthUserService {

    @JsonRpcMethod(value = "user.create")
    Long userCreateRequest(
            @JsonRpcParam(value = "request") AuthUserCreateDTO createDTO
    );

    @JsonRpcMethod(value = "user.get.by.id")
    AuthUserDTO userGetByIdRequest(
            @JsonRpcParam(value = "id") Long id
    );

    @JsonRpcMethod("user.get.all")
    List<AuthUserDTO> userGetAllRequest();
}
