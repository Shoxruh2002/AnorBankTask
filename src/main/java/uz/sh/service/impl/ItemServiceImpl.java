package uz.sh.service.impl;

import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceImpl;
import org.springframework.stereotype.Service;
import uz.sh.dto.item.ItemCreateDTO;
import uz.sh.dto.item.ItemDTO;
import uz.sh.dto.item.ItemDetailDTO;
import uz.sh.entity.Item;
import uz.sh.exceptions.BadRequestException;
import uz.sh.exceptions.NotFoundException;
import uz.sh.mapper.ItemMapper;
import uz.sh.repository.ItemRepository;
import uz.sh.service.AbstractService;
import uz.sh.service.ItemService;

import java.util.List;
import java.util.Optional;

/**
 * Author: Shoxruh Bekpulatov
 * Time: 4/20/23 9:33 AM
 **/
@AutoJsonRpcServiceImpl
@Service
public class ItemServiceImpl extends AbstractService<ItemRepository, ItemMapper> implements ItemService {

    private final BuildingServiceImpl buildingService;

    public ItemServiceImpl(ItemRepository repository, ItemMapper mapper, BuildingServiceImpl buildingService) {
        super(repository, mapper);
        this.buildingService = buildingService;
    }

    @Override
    public Long itemCreate(ItemCreateDTO createDTO) {
        Item item = mapper.fromCreateDTO(createDTO);
        Item saved = repository.save(item);
        return saved.getId();
    }

    @Override
    public ItemDTO itemGetById(Long id) {
        Optional<Item> optionalItem = repository.findById(id);
        if (optionalItem.isEmpty())
            throw new NotFoundException("Item Not found with id : " + id);
        Item item = optionalItem.get();
        return mapper.toDTO(item);
    }

    @Override
    public ItemDetailDTO itemDetailGetById(Long id) {
        Optional<Item> optionalItem = repository.findById(id);
        if (optionalItem.isEmpty())
            throw new NotFoundException("Item Not found with id : " + id);
        Item item = optionalItem.get();
        ItemDetailDTO detailsDTO = mapper.toDetailsDTO(item);
        return detailsDTO;
    }

    @Override
    public List<ItemDTO> itemGetAll() {
        List<Item> itemList = repository.findAll();
        return mapper.toDTO(itemList);
    }

    @Override
    public Long itemDelete(Long id) {
        Optional<Item> optionalItem = repository.findById(id);
        if (optionalItem.isEmpty())
            throw new BadRequestException("Item Not found with id : " + id);
        repository.delete(optionalItem.get());
        return optionalItem.get().getId();
    }

    @Override
    public Long itemUpdate(ItemDTO updateDTO) {
        Long id = updateDTO.getId();
        Optional<Item> optionalItem = repository.findById(id);
        if (optionalItem.isEmpty())
            throw new BadRequestException("Item Not found with id : " + id);
        Item item = mapper.fromUpdateDTO(updateDTO, optionalItem.get());
        repository.save(item);
        return id;
    }
}
