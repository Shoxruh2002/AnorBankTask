package uz.sh.service.impl;

import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceImpl;
import org.springframework.stereotype.Service;
import uz.sh.dto.complex.ComplexCreateDTO;
import uz.sh.dto.complex.ComplexDTO;
import uz.sh.dto.complex.ComplexDetailDTO;
import uz.sh.entity.Complex;
import uz.sh.exceptions.BadRequestException;
import uz.sh.exceptions.NotFoundException;
import uz.sh.mapper.ComplexMapper;
import uz.sh.repository.ComplexRepository;
import uz.sh.service.AbstractService;
import uz.sh.service.ComplexService;
import uz.sh.service.ComplexService;

import java.util.List;
import java.util.Optional;

/**
 * Author: Shoxruh Bekpulatov
 * Time: 4/20/23 9:33 AM
 **/
@AutoJsonRpcServiceImpl
@Service
public class ComplexServiceImpl  extends AbstractService<ComplexRepository, ComplexMapper> implements ComplexService {

    private final BuildingServiceImpl buildingService;

    public ComplexServiceImpl(ComplexRepository repository, ComplexMapper mapper, BuildingServiceImpl buildingService) {
        super(repository, mapper);
        this.buildingService = buildingService;
    }

    @Override
    public Long complexCreate(ComplexCreateDTO createDTO) {
        Complex complex = mapper.fromCreateDTO(createDTO);
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
        return detailsDTO;
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
    public Long complexUpdate(ComplexDTO updateDTO) {
        Long id = updateDTO.getId();
        Optional<Complex> optionalComplex = repository.findById(id);
        if (optionalComplex.isEmpty())
            throw new BadRequestException("Complex Not found with id : " + id);
        Complex complex = mapper.fromUpdateDTO(updateDTO, optionalComplex.get());
        repository.save(complex);
        return id;
    }
}
