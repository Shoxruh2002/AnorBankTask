package uz.sh.repository;

import uz.sh.entity.Room;

import java.util.List;

/**
 * Author: Shoxruh Bekpulatov
 * Time: 4/20/23 9:28 AM
 **/
public interface RoomRepository extends BaseRepository<Room> {

    List<Room> findAllByFloor_Id(Long floorId);
}
