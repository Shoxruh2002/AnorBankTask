package uz.sh.service;

import com.googlecode.jsonrpc4j.JsonRpcMethod;
import com.googlecode.jsonrpc4j.JsonRpcParam;
import com.googlecode.jsonrpc4j.JsonRpcService;
import uz.sh.dto.item.ItemCreateDTO;
import uz.sh.dto.item.ItemDTO;
import uz.sh.dto.item.ItemDetailDTO;
import uz.sh.dto.item.ItemUpdateDTO;

import java.util.List;

/**
 * Author: Shoxruh Bekpulatov
 * Time: 4/20/23 9:31 AM
 **/
@JsonRpcService("/api/v1/anor/task/item")
public interface ItemService {

    @JsonRpcMethod(value = "item.create")
    Long createItem(
            @JsonRpcParam(value = "body") ItemCreateDTO createDTO
    );

    @JsonRpcMethod(value = "item.get.by.id")
    ItemDTO getItemDTOById(
            @JsonRpcParam(value = "id") Long id
    );

    @JsonRpcMethod(value = "item.get.detail.by.id")
    ItemDetailDTO getItemDetailById(
            @JsonRpcParam(value = "id") Long id
    );

    @JsonRpcMethod("item.get.all")
    List<ItemDTO> getAllItem();

    @JsonRpcMethod("item.delete")
    Long deleteItem(
            @JsonRpcParam(value = "id") Long id
    );

    @JsonRpcMethod("item.update")
    Long updateItem(
            @JsonRpcParam(value = "body") ItemUpdateDTO updateDTO
    );

    @JsonRpcMethod("item.bind.to.complex")
    Long bindItemToComplex(
            @JsonRpcParam(value = "complexId") Long complexId,
            @JsonRpcParam(value = "itemId") Long itemId
    );

    @JsonRpcMethod("item.unbind.to.complex")
    Long unbindItemToComplex(
            @JsonRpcParam(value = "itemId") Long itemId
    );

}
