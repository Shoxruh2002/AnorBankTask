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
@JsonRpcService("/api/v1/anor/task/organization")
public interface OrganizationService {

    @JsonRpcMethod(value = "organization.create")
    Long createOrganization(
            @JsonRpcParam(value = "body") OrganizationCreateDTO createDTO
    );

    @JsonRpcMethod(value = "organization.get.by.id")
    OrganizationDTO getOrganizationDTOById(
            @JsonRpcParam(value = "id") Long id
    );

    @JsonRpcMethod(value = "organization.get.detail.by.id")
    OrganizationDetailDTO getOrganizationDetailById(
            @JsonRpcParam(value = "id") Long id
    );

    @JsonRpcMethod("organization.get.all")
    List<OrganizationDTO> getAllOrganization();

    @JsonRpcMethod("organization.delete")
    Long deleteOrganization(
            @JsonRpcParam(value = "id") Long id
    );

    @JsonRpcMethod("organization.update")
    Long updateOrganization(
            @JsonRpcParam(value = "body") OrganizationDTO updateDTO
    );

}
