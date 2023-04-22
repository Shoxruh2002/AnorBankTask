package uz.sh.utils;

import uz.sh.dto.organization.OrganizationDTO;
import uz.sh.dto.organization.OrganizationDetailDTO;
import uz.sh.entity.Building;
import uz.sh.entity.Organization;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Shoxruh Bekpulatov
 * Time : 22/04/23
 */

public class TestingUtils {
    public static final String ORGANIZATION_NAME = "ORGANIZATION";
    public static final String ORGANIZATION_ADDRESS = "ADDRESS";
    public static final String BUILDING_NAME = "BUILDING";

    public static Organization getOrganizationEntity() {
        Organization organization = new Organization(ORGANIZATION_NAME, ORGANIZATION_ADDRESS, null);
        organization.setId(1L);
        return organization;
    }

    public static OrganizationDTO getOrganizationDTO() {
        OrganizationDTO organizationDTO = new OrganizationDTO(ORGANIZATION_NAME, ORGANIZATION_ADDRESS);
        organizationDTO.setId(1L);
        return organizationDTO;
    }

    public static OrganizationDetailDTO getOrganizationDetailDTO() {
        OrganizationDetailDTO organizationDetailDTO = new OrganizationDetailDTO(ORGANIZATION_NAME, ORGANIZATION_ADDRESS, new ArrayList<>());
        organizationDetailDTO.setId(1L);
        return organizationDetailDTO;
    }
}
