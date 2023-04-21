package uz.sh.service;

import com.googlecode.jsonrpc4j.JsonRpcMethod;
import com.googlecode.jsonrpc4j.JsonRpcParam;
import com.googlecode.jsonrpc4j.JsonRpcService;
import uz.sh.dto.room.RoomCreateDTO;
import uz.sh.dto.room.RoomDTO;
import uz.sh.dto.room.RoomDetailDTO;
import uz.sh.dto.room.RoomUpdateDTO;

import java.util.List;

/**
 * Author: Shoxruh Bekpulatov
 * Time: 4/20/23 9:30 AM
 **/
@JsonRpcService("/api/v1/anor/task/room")
public interface RoomService {

    @JsonRpcMethod(value = "room.create")
    Long createRoom(
            @JsonRpcParam(value = "body") RoomCreateDTO createDTO
    );

    @JsonRpcMethod(value = "room.get.by.id")
    RoomDTO getRoomDTOById(
            @JsonRpcParam(value = "id") Long id
    );

    @JsonRpcMethod(value = "room.get.detail.by.id")
    RoomDetailDTO getRoomDetailById(
            @JsonRpcParam(value = "id") Long id
    );

    @JsonRpcMethod("room.get.all")
    List<RoomDTO> getAllRoomDTO();

    @JsonRpcMethod("room.delete")
    Long deleteRoom(
            @JsonRpcParam(value = "id") Long id
    );

    @JsonRpcMethod("room.update")
    Long updateRoom(
            @JsonRpcParam(value = "body") RoomUpdateDTO updateDTO
    );

}
