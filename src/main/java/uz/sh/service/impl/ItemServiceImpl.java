package uz.sh.service.impl;

import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceImpl;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import uz.sh.dto.item.ItemCreateDTO;
import uz.sh.dto.item.ItemDTO;
import uz.sh.dto.item.ItemDetailDTO;
import uz.sh.dto.item.ItemUpdateDTO;
import uz.sh.entity.Item;
import uz.sh.entity.Room;
import uz.sh.exceptions.BadRequestException;
import uz.sh.exceptions.NotFoundException;
import uz.sh.mapper.ItemMapper;
import uz.sh.repository.ItemRepository;
import uz.sh.service.AbstractService;
import uz.sh.service.ItemService;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Author: Shoxruh Bekpulatov
 * Time: 4/20/23 9:33 AM
 **/
@AutoJsonRpcServiceImpl
@Service
public class ItemServiceImpl extends AbstractService<ItemRepository, ItemMapper> implements ItemService {

    private final RoomServiceImpl roomServiceImpl;
    private final ComplexServiceImpl complexService;

    public ItemServiceImpl(ItemRepository repository, ItemMapper mapper, @Lazy RoomServiceImpl roomServiceImpl, @Lazy ComplexServiceImpl complexService) {
        super(repository, mapper);
        this.roomServiceImpl = roomServiceImpl;
        this.complexService = complexService;
    }

    @Override
    public Long itemCreate(ItemCreateDTO createDTO) {
        Item item = mapper.fromCreateDTO(createDTO);
        Room room = roomServiceImpl.getRoomById(createDTO.getRoomId());
        item.setRoom(room);
        if (Objects.nonNull(createDTO.getComplexId()))
            item.setComplex(complexService.getComplexById(createDTO.getComplexId()));
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
    public ItemDetailDTO itemGetDetailById(Long id) {
        Optional<Item> optionalItem = repository.findById(id);
        if (optionalItem.isEmpty())
            throw new NotFoundException("Item Not found with id : " + id);
        Item item = optionalItem.get();
        return mapper.toDetailDTO(item);

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
    public Long itemUpdate(ItemUpdateDTO updateDTO) {
        Long id = updateDTO.getId();
        Optional<Item> optionalItem = repository.findById(id);
        if (optionalItem.isEmpty())
            throw new BadRequestException("Item Not found with id : " + id);
        Item item = mapper.fromUpdateDTO(updateDTO, optionalItem.get());
        repository.save(item);
        return id;
    }

    @Override
    public Long itemBindToComplex(Long complexId, Long itemId) {
        complexService.getComplexById(complexId);
        this.itemGetById(itemId);
        repository.itemBindToComplex(itemId, complexId);
        return itemId;
    }

    @Override
    public Long itemBindToComplex(Long itemId) {
        this.itemGetById(itemId);
        repository.itemBindToComplex(itemId, null);
        return itemId;
    }

    public List<ItemDTO> getItemsByComplexId(Long id) {
        List<Item> itemList = repository.findAllByComplex_Id(id);
        return mapper.toDTO(itemList);
    }

    public List<ItemDTO> getItemsByRoomId(Long id) {
        List<Item> itemList = repository.findAllByRoom_Id(id);
        return mapper.toDTO(itemList);
    }
}
