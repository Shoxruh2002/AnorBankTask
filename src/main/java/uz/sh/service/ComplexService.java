package uz.sh.service;

import com.googlecode.jsonrpc4j.JsonRpcMethod;
import com.googlecode.jsonrpc4j.JsonRpcParam;
import com.googlecode.jsonrpc4j.JsonRpcService;
import uz.sh.dto.complex.ComplexCreateDTO;
import uz.sh.dto.complex.ComplexDTO;
import uz.sh.dto.complex.ComplexDetailDTO;
import uz.sh.dto.complex.ComplexUpdateDTO;

import java.util.List;

/**
 * Author: Shoxruh Bekpulatov
 * Time: 4/20/23 9:31 AM
 **/
@JsonRpcService("/api/v1/anor/task/complex")
public interface ComplexService {

    @JsonRpcMethod(value = "complex.create")
    Long complexCreate(
            @JsonRpcParam(value = "body") ComplexCreateDTO createDTO
    );

    @JsonRpcMethod(value = "complex.get.by.id")
    ComplexDTO complexGetById(
            @JsonRpcParam(value = "id") Long id
    );

    @JsonRpcMethod(value = "complex.get.detail.by.id")
    ComplexDetailDTO complexDetailGetById(
            @JsonRpcParam(value = "id") Long id
    );

    @JsonRpcMethod("complex.get.all")
    List<ComplexDTO> complexGetAll();

    @JsonRpcMethod("complex.delete")
    Long complexDelete(
            @JsonRpcParam(value = "id") Long id
    );

    @JsonRpcMethod("complex.update")
    Long complexUpdate(
            @JsonRpcParam(value = "body") ComplexUpdateDTO updateDTO
    );

    @JsonRpcMethod("complex.bind.to.user")
    Long complexBindToUser(
            @JsonRpcParam(value = "complexId") Long  complexId,
            @JsonRpcParam(value = "userId") Long  userId
    );

    @JsonRpcMethod("complex.unbind.to.user")
    Long complexUnBindToUser(
            @JsonRpcParam(value = "complexId") Long  complexId
    );

}
