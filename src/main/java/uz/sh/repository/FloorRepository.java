package uz.sh.repository;

import uz.sh.entity.Floor;

import java.util.List;

/**
 * Author: Shoxruh Bekpulatov
 * Time: 4/20/23 9:26 AM
 **/
public interface FloorRepository extends BaseRepository<Floor> {

    List<Floor> findAllByBuilding_Id(Long id);
}
