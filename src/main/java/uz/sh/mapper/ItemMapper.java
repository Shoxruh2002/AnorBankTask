package uz.sh.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;
import uz.sh.dto.item.ItemCreateDTO;
import uz.sh.dto.item.ItemDTO;
import uz.sh.dto.item.ItemDetailDTO;
import uz.sh.dto.item.ItemUpdateDTO;
import uz.sh.entity.Item;

import java.util.List;

/**
 * Author: Shoxruh Bekpulatov
 * Time: 4/20/23 5:35 PM
 **/
@Component
@Mapper(componentModel = "spring")
public interface ItemMapper extends BaseMapper {

    Item fromCreateDTO(ItemCreateDTO createDTO);

    ItemDTO toDTO(Item items);

    ItemDetailDTO toDetailDTO(Item items);

    List<ItemDTO> toDTO(List<Item> items);

    Item fromUpdateDTO(ItemUpdateDTO updateDTO, @MappingTarget Item item);


}
