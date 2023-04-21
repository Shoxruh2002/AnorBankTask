package uz.sh.repository;

import uz.sh.entity.Building;

import java.util.List;

/**
 * Author: Shoxruh Bekpulatov
 * Time: 4/20/23 9:25 AM
 **/
public interface BuildingRepository extends BaseRepository<Building> {

    List<Building> findAllByOrganization_Id(Long organizationId);
}
