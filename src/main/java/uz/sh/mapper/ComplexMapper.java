package uz.sh.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;
import uz.sh.dto.complex.ComplexCreateDTO;
import uz.sh.dto.complex.ComplexDTO;
import uz.sh.dto.complex.ComplexDetailDTO;
import uz.sh.dto.complex.ComplexUpdateDTO;
import uz.sh.entity.Complex;

import java.util.List;

/**
 * Author: Shoxruh Bekpulatov
 * Time: 4/20/23 5:39 PM
 **/
@Component
@Mapper(componentModel = "spring")
public interface ComplexMapper extends BaseMapper {

    Complex fromCreateDTO(ComplexCreateDTO createDTO);

    ComplexDTO toDTO(Complex complexes);

    ComplexDetailDTO toDetailsDTO(Complex complexes);

    List<ComplexDTO> toDTO(List<Complex> complexes);

    Complex fromUpdateDTO(ComplexUpdateDTO updateDTO, @MappingTarget Complex complex);

}
