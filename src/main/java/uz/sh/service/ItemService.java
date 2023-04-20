package uz.sh.service;

import com.googlecode.jsonrpc4j.JsonRpcMethod;
import com.googlecode.jsonrpc4j.JsonRpcParam;
import com.googlecode.jsonrpc4j.JsonRpcService;
import uz.sh.dto.item.ItemCreateDTO;
import uz.sh.dto.item.ItemDTO;
import uz.sh.dto.item.ItemDetailDTO;

import java.util.List;

/**
 * Author: Shoxruh Bekpulatov
 * Time: 4/20/23 9:31 AM
 **/
@JsonRpcService("/api/v1/anor/task")
public interface ItemService {

    @JsonRpcMethod(value = "item.create")
    Long itemCreate(
            @JsonRpcParam(value = "body") ItemCreateDTO createDTO
    );

    @JsonRpcMethod(value = "item.get.by.id")
    ItemDTO itemGetById(
            @JsonRpcParam(value = "id") Long id
    );

    @JsonRpcMethod(value = "item.get.detail.by.id")
    ItemDetailDTO itemDetailGetById(
            @JsonRpcParam(value = "id") Long id
    );

    @JsonRpcMethod("item.get.all")
    List<ItemDTO> itemGetAll();

    @JsonRpcMethod("item.delete")
    Long itemDelete(
            @JsonRpcParam(value = "id") Long id
    );

    @JsonRpcMethod("item.delete")
    Long itemUpdate(
            @JsonRpcParam(value = "body") ItemDTO updateDTO
    );

}
