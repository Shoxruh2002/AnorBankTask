package uz.sh.service;

import com.googlecode.jsonrpc4j.JsonRpcMethod;
import com.googlecode.jsonrpc4j.JsonRpcParam;
import com.googlecode.jsonrpc4j.JsonRpcService;
import uz.sh.dto.floor.FloorCreateDTO;
import uz.sh.dto.floor.FloorDTO;
import uz.sh.dto.floor.FloorDetailDTO;

import java.util.List;

/**
 * Author: Shoxruh Bekpulatov
 * Time: 4/20/23 9:31 AM
 **/
@JsonRpcService("/api/v1/anor/task/floor")
public interface FloorService {

    @JsonRpcMethod(value = "floor.create")
    Long floorCreate(
            @JsonRpcParam(value = "body") FloorCreateDTO createDTO
    );

    @JsonRpcMethod(value = "floor.get.by.id")
    FloorDTO floorGetById(
            @JsonRpcParam(value = "id") Long id
    );

    @JsonRpcMethod(value = "floor.get.detail.by.id")
    FloorDetailDTO floorDetailGetById(
            @JsonRpcParam(value = "id") Long id
    );

    @JsonRpcMethod("floor.get.all")
    List<FloorDTO> floorGetAll();

    @JsonRpcMethod("floor.delete")
    Long floorDelete(
            @JsonRpcParam(value = "id") Long id
    );

    @JsonRpcMethod("floor.update")
    Long floorUpdate(
            @JsonRpcParam(value = "body") FloorDTO updateDTO
    );

}
