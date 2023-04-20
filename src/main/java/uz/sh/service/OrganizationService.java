package uz.sh.service;

import com.googlecode.jsonrpc4j.JsonRpcMethod;
import com.googlecode.jsonrpc4j.JsonRpcParam;
import com.googlecode.jsonrpc4j.JsonRpcService;
import uz.sh.dto.organization.OrganizationCreateDTO;
import uz.sh.dto.organization.OrganizationDTO;
import uz.sh.dto.organization.OrganizationDetailDTO;

import java.util.List;

/**
 * Author: Shoxruh Bekpulatov
 * Time: 4/20/23 9:30 AM
 **/
@JsonRpcService("/api/v1/anor/task")
public interface OrganizationService {

    @JsonRpcMethod(value = "organization.create")
    Long organizationCreate(
            @JsonRpcParam(value = "body") OrganizationCreateDTO createDTO
    );

    @JsonRpcMethod(value = "organization.get.by.id")
    OrganizationDTO organizationGetById(
            @JsonRpcParam(value = "id") Long id
    );

    @JsonRpcMethod(value = "organization.get.detail.by.id")
    OrganizationDetailDTO organizationDetailGetById(
            @JsonRpcParam(value = "id") Long id
    );

    @JsonRpcMethod("organization.get.all")
    List<OrganizationDTO> organizationGetAll();

    @JsonRpcMethod("organization.delete")
    Long organizationDelete(
            @JsonRpcParam(value = "id") Long id
    );

    @JsonRpcMethod("organization.delete")
    Long organizationUpdate(
            @JsonRpcParam(value = "body") OrganizationDTO updateDTO
    );

}
