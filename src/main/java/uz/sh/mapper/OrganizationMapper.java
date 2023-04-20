package uz.sh.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;
import uz.sh.dto.organization.OrganizationCreateDTO;
import uz.sh.dto.organization.OrganizationDTO;
import uz.sh.dto.organization.OrganizationDetailDTO;
import uz.sh.entity.Organization;

import java.util.List;

/**
 * Author: Shoxruh Bekpulatov
 * Time: 4/20/23 4:59 PM
 **/
@Component
@Mapper(componentModel = "spring")
public interface OrganizationMapper extends BaseMapper {

    Organization fromCreateDTO(OrganizationCreateDTO createDTO);

    OrganizationDTO toDTO(Organization organizations);

    OrganizationDetailDTO toDetailsDTO(Organization organizations);

    List<OrganizationDTO> toDTO(List<Organization> organizations);

    Organization fromUpdateDTO(OrganizationDTO updateDTO, @MappingTarget Organization organization);

}
