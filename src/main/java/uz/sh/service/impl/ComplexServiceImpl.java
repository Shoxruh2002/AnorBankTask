package uz.sh.service.impl;

import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import uz.sh.contraints.ConstMessages;
import uz.sh.dto.complex.ComplexCreateDTO;
import uz.sh.dto.complex.ComplexDTO;
import uz.sh.dto.complex.ComplexDetailDTO;
import uz.sh.dto.complex.ComplexUpdateDTO;
import uz.sh.entity.AuthUser;
import uz.sh.entity.Complex;
import uz.sh.entity.Room;
import uz.sh.exceptions.NotFoundException;
import uz.sh.mapper.ComplexMapper;
import uz.sh.repository.ComplexRepository;
import uz.sh.service.AbstractService;
import uz.sh.service.ComplexService;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Author: Shoxruh Bekpulatov
 * Time: 4/20/23 9:33 AM
 **/
@AutoJsonRpcServiceImpl
@Service
public class ComplexServiceImpl extends AbstractService<ComplexRepository, ComplexMapper> implements ComplexService {

    private final ItemServiceImpl itemService;
    private final RoomServiceImpl roomService;
    private final AuthUserServiceImpl authUserService;

    public ComplexServiceImpl(ComplexRepository repository, @Qualifier("complexMapperImpl") ComplexMapper mapper, ItemServiceImpl itemService, @Lazy RoomServiceImpl roomService, @Lazy AuthUserServiceImpl authUserService) {
        super(repository, mapper);
        this.itemService = itemService;
        this.roomService = roomService;
        this.authUserService = authUserService;
    }

    /**
     * Creates new Complex
     *
     * @param createDTO -> dto from comes from FrontEnd for creating new Complex
     * @return id of new created Complex
     */

    @Override
    public Long createComplex(ComplexCreateDTO createDTO) {
        Complex complex = mapper.fromCreateDTO(createDTO);
        if (Objects.nonNull(createDTO.getAuthUserId())) {
            AuthUser authUser = authUserService.getAuthUserById(createDTO.getAuthUserId());
            complex.setAuthUser(authUser);
        }
        Room room = roomService.getRoomById(createDTO.getRoomId());
        complex.setRoom(room);
        Complex saved = repository.save(complex);
        return saved.getId();
    }

    /**
     * @param id -> id of Complex
     * @return short Info of Complex
     */
    @Override
    public ComplexDTO getComplexDTOById(Long id) {
        Complex complex = this.getComplexById(id);
        return mapper.toDTO(complex);
    }

    /**
     * @param id -> id of Complex
     * @return Full Info of Complex with all items and user
     */


    @Override
    public ComplexDetailDTO getComplexDetailById(Long id) {
        Complex complex = this.getComplexById(id);
        ComplexDetailDTO detailsDTO = mapper.toDetailsDTO(complex);
        detailsDTO.setItems(itemService.getItemsByComplexId(id));
        return detailsDTO;
    }


    /**
     * @param id -> id of Complex
     * @return finds Complex from database and returns it if found
     * @throws NotFoundException if not found Complex
     */
    public Complex getComplexById(Long id) {
        Optional<Complex> optionalComplex = repository.findById(id);
        if (optionalComplex.isPresent())
            return optionalComplex.get();
        throw new NotFoundException(ConstMessages.COMPLEX_NOT_FOUND.formatted(id));
    }

    /**
     * @return all the complexes  in database
     */


    @Override
    public List<ComplexDTO> getAllComplex() {
        List<Complex> complexList = repository.findAll();
        return mapper.toDTO(complexList);
    }

    /**
     * @param id the id of the Complex to be deleted
     * @return id of deleted Complex
     */


    @Override
    public Long deleteComplex(Long id) {
        Complex dbComplex = this.getComplexById(id);
        repository.delete(dbComplex);
        return dbComplex.getId();
    }

    /**
     * @param updateDTO dto to be updated
     * @return id of updated Complex
     */

    @Override
    public Long updateComplex(ComplexUpdateDTO updateDTO) {
        Long id = updateDTO.getId();
        Complex dbComplex = this.getComplexById(id);
        Complex complex = mapper.fromUpdateDTO(updateDTO, dbComplex);
        repository.save(complex);
        return id;
    }

    @Override
    public Long bindComplexToUser(Long complexId, Long userId) {
        this.getComplexById(complexId);
        authUserService.getAuthUserById(userId);
        repository.bindComplexToUser(complexId, userId);
        return complexId;
    }

    @Override
    public Long unbindComplexToUser(Long complexId) {
        this.getComplexById(complexId);
        repository.bindComplexToUser(complexId, null);
        return complexId;
    }

    /**
     * @param userId -> id of the organization
     * @return all complexes of organization
     */

    public List<ComplexDTO> getByAuthUserId(Long userId) {
        return mapper.toDTO(repository.findAllByAuthUser_Id(userId));
    }

    /**
     * @param roomId -> id of the organization
     * @return all complexes of organization
     */

    public List<ComplexDTO> getByRoomId(Long roomId) {
        return mapper.toDTO(repository.findAllByRoom_Id(roomId));
    }
}
