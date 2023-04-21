package uz.sh.service.impl;

import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import uz.sh.contraints.ConstMessages;
import uz.sh.dto.item.ItemCreateDTO;
import uz.sh.dto.item.ItemDTO;
import uz.sh.dto.item.ItemDetailDTO;
import uz.sh.dto.item.ItemUpdateDTO;
import uz.sh.entity.Item;
import uz.sh.entity.Room;
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

    public ItemServiceImpl(ItemRepository repository, @Qualifier("itemMapperImpl") ItemMapper mapper, @Lazy RoomServiceImpl roomServiceImpl, @Lazy ComplexServiceImpl complexService) {
        super(repository, mapper);
        this.roomServiceImpl = roomServiceImpl;
        this.complexService = complexService;
    }

    /**
     * Creates new Item
     *
     * @param createDTO -> dto from comes from FrontEnd for creating new Item
     * @return id of new created Item
     */

    @Override
    public Long createItem(ItemCreateDTO createDTO) {
        Item item = mapper.fromCreateDTO(createDTO);
        Room room = roomServiceImpl.getRoomById(createDTO.getRoomId());
        item.setRoom(room);
        if (Objects.nonNull(createDTO.getComplexId()))
            item.setComplex(complexService.getComplexById(createDTO.getComplexId()));
        Item saved = repository.save(item);
        return saved.getId();
    }

    /**
     * @param id -> id of Item
     * @return short Info of Item
     */

    @Override
    public ItemDTO getItemDTOById(Long id) {
        Item item = this.getItemById(id);
        return mapper.toDTO(item);
    }

    /**
     * @param id -> id of Item
     * @return Full Info of Item with all rooms and complex
     */


    @Override
    public ItemDetailDTO getItemDetailById(Long id) {
        Item item = this.getItemById(id);
        return mapper.toDetailDTO(item);
    }

    /**
     * @param id -> id of Item
     * @return finds Item from database and returns it if found
     * @throws NotFoundException if not found Item
     */

    private Item getItemById(Long id) {
        Optional<Item> optionalItem = repository.findById(id);
        if (optionalItem.isEmpty())
            throw new NotFoundException(ConstMessages.ITEM_NOT_FOUND.formatted(id));
        return optionalItem.get();
    }

    /**
     * @return all the items  in database
     */


    @Override
    public List<ItemDTO> getAllItem() {
        List<Item> itemList = repository.findAll();
        return mapper.toDTO(itemList);
    }

    /**
     * @param id the id of the Item to be deleted
     * @return id of deleted Item
     */


    @Override
    public Long deleteItem(Long id) {
        Item dbItem = this.getItemById(id);
        repository.delete(dbItem);
        return dbItem.getId();
    }

    /**
     * @param updateDTO dto to be updated
     * @return id of updated Item
     */

    @Override
    public Long updateItem(ItemUpdateDTO updateDTO) {
        Long id = updateDTO.getId();
        Item dbItem = this.getItemById(id);
        Item item = mapper.fromUpdateDTO(updateDTO, dbItem);
        repository.save(item);
        return id;
    }


    /**
     *  binds item to complex and item will be dependent on complex and room
     * @param itemId ->  item id to be bind
     * @return id of item bind
     */



    @Override
    public Long bindItemToComplex(Long complexId, Long itemId) {
        complexService.getComplexById(complexId);
        this.getItemById(itemId);
        repository.itemBindToComplex(itemId, complexId);
        return itemId;
    }

    /**
     *  unbinds item from complex and item will be dependent on room only
     * @param itemId ->  item id to be unbind
     * @return id of item unbind
     */

    @Override
    public Long unbindItemToComplex(Long itemId) {
        this.getItemById(itemId);
        repository.itemBindToComplex(itemId, null);
        return itemId;
    }

    /**
     * @param id -> id of the complex
     * @return all items of complex
     */

    public List<ItemDTO> getItemsByComplexId(Long id) {
        List<Item> itemList = repository.findAllByComplex_Id(id);
        return mapper.toDTO(itemList);
    }

    /**
     * @param id -> id of the room
     * @return all items of room
     */

    public List<ItemDTO> getItemsByRoomId(Long id) {
        List<Item> itemList = repository.findAllByRoom_Id(id);
        return mapper.toDTO(itemList);
    }
}
