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
    Long createComplex(
            @JsonRpcParam(value = "body") ComplexCreateDTO createDTO
    );

    @JsonRpcMethod(value = "complex.get.by.id")
    ComplexDTO getComplexDTOById(
            @JsonRpcParam(value = "id") Long id
    );

    @JsonRpcMethod(value = "complex.get.detail.by.id")
    ComplexDetailDTO getComplexDetailById(
            @JsonRpcParam(value = "id") Long id
    );

    @JsonRpcMethod("complex.get.all")
    List<ComplexDTO> getAllComplex();

    @JsonRpcMethod("complex.delete")
    Long deleteComplex(
            @JsonRpcParam(value = "id") Long id
    );

    @JsonRpcMethod("complex.update")
    Long updateComplex(
            @JsonRpcParam(value = "body") ComplexUpdateDTO updateDTO
    );

    @JsonRpcMethod("complex.bind.to.user")
    Long bindComplexToUser(
            @JsonRpcParam(value = "complexId") Long  complexId,
            @JsonRpcParam(value = "userId") Long  userId
    );

    @JsonRpcMethod("complex.unbind.to.user")
    Long unbindComplexToUser(
            @JsonRpcParam(value = "complexId") Long  complexId
    );

}
