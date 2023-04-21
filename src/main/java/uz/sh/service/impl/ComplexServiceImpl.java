package uz.sh.service.impl;

import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceImpl;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import uz.sh.dto.complex.ComplexCreateDTO;
import uz.sh.dto.complex.ComplexDTO;
import uz.sh.dto.complex.ComplexDetailDTO;
import uz.sh.dto.complex.ComplexUpdateDTO;
import uz.sh.entity.AuthUser;
import uz.sh.entity.Complex;
import uz.sh.entity.Room;
import uz.sh.exceptions.BadRequestException;
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

    public ComplexServiceImpl(ComplexRepository repository, ComplexMapper mapper, ItemServiceImpl itemService, @Lazy RoomServiceImpl roomService, @Lazy AuthUserServiceImpl authUserService) {
        super(repository, mapper);
        this.itemService = itemService;
        this.roomService = roomService;
        this.authUserService = authUserService;
    }

    @Override
    public Long complexCreate(ComplexCreateDTO createDTO) {
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

    @Override
    public ComplexDTO complexGetById(Long id) {
        Optional<Complex> optionalComplex = repository.findById(id);
        if (optionalComplex.isEmpty())
            throw new NotFoundException("Complex Not found with id : " + id);
        Complex complex = optionalComplex.get();
        return mapper.toDTO(complex);
    }

    @Override
    public ComplexDetailDTO complexDetailGetById(Long id) {
        Optional<Complex> optionalComplex = repository.findById(id);
        if (optionalComplex.isEmpty())
            throw new NotFoundException("Complex Not found with id : " + id);
        Complex complex = optionalComplex.get();
        ComplexDetailDTO detailsDTO = mapper.toDetailsDTO(complex);
        detailsDTO.setItems(itemService.getItemsByComplexId(id));
        return detailsDTO;
    }

    public Complex getComplexById(Long id) {
        Optional<Complex> optionalComplex = repository.findById(id);
        if (optionalComplex.isPresent())
            return optionalComplex.get();
        throw new NotFoundException("Complex not found with id " + id);
    }

    @Override
    public List<ComplexDTO> complexGetAll() {
        List<Complex> complexList = repository.findAll();
        return mapper.toDTO(complexList);
    }

    @Override
    public Long complexDelete(Long id) {
        Optional<Complex> optionalComplex = repository.findById(id);
        if (optionalComplex.isEmpty())
            throw new BadRequestException("Complex Not found with id : " + id);
        repository.delete(optionalComplex.get());
        return optionalComplex.get().getId();
    }

    @Override
    public Long complexUpdate(ComplexUpdateDTO updateDTO) {
        Long id = updateDTO.getId();
        Optional<Complex> optionalComplex = repository.findById(id);
        if (optionalComplex.isEmpty())
            throw new BadRequestException("Complex Not found with id : " + id);
        Complex complex = mapper.fromUpdateDTO(updateDTO, optionalComplex.get());
        repository.save(complex);
        return id;
    }

    @Override
    public Long complexBindToUser(Long complexId, Long userId) {
        this.getComplexById(complexId);
        authUserService.getAuthUserById(userId);
        repository.bindComplexToUser(complexId, userId);
        return complexId;
    }

    @Override
    public Long complexUnBindToUser(Long complexId) {
        this.getComplexById(complexId);
        repository.bindComplexToUser(complexId, null);
        return complexId;
    }

    public List<ComplexDTO> getByAuthUserId(Long userId) {
        return mapper.toDTO(repository.findAllByAuthUser_Id(userId));
    }

    public List<ComplexDTO> getByRoomId(Long roomId) {
        return mapper.toDTO(repository.findAllByRoom_Id(roomId));
    }
}
