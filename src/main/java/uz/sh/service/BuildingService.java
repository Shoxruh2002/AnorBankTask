package uz.sh.service;

import com.googlecode.jsonrpc4j.JsonRpcMethod;
import com.googlecode.jsonrpc4j.JsonRpcParam;
import com.googlecode.jsonrpc4j.JsonRpcService;
import uz.sh.dto.building.BuildingCreateDTO;
import uz.sh.dto.building.BuildingDTO;
import uz.sh.dto.building.BuildingDetailDTO;

import java.util.List;

/**
 * Author: Shoxruh Bekpulatov
 * Time: 4/20/23 9:31 AM
 **/
@JsonRpcService("/api/v1/anor/task/building")
public interface BuildingService {

    @JsonRpcMethod(value = "building.create")
    Long createBuilding(
            @JsonRpcParam(value = "body") BuildingCreateDTO createDTO
    );

    @JsonRpcMethod(value = "building.get.by.id")
    BuildingDTO getBuildingDTOById(
            @JsonRpcParam(value = "id") Long id
    );

    @JsonRpcMethod(value = "building.get.detail.by.id")
    BuildingDetailDTO getBuildingDetailById(
            @JsonRpcParam(value = "id") Long id
    );

    @JsonRpcMethod("building.get.all")
    List<BuildingDTO> getAllBuilding();

    @JsonRpcMethod("building.delete")
    Long deleteBuilding(
            @JsonRpcParam(value = "id") Long id
    );

    @JsonRpcMethod("building.update")
    Long updateBuilding(
            @JsonRpcParam(value = "body") BuildingDTO updateDTO
    );
}
