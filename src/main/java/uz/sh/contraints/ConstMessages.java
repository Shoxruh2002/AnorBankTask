package uz.sh.contraints;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Author: Shoxruh Bekpulatov
 * Time: 4/20/23 9:09 AM
 **/
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ConstMessages {

    public static final String AUTH_USER_NOT_FOUND = "Auth User not found with id : %s ";
    public static final String ITEM_NOT_FOUND = "Item not found with id : %s ";
    public static final String COMPLEX_NOT_FOUND = "Complex not found with id : %s ";
    public static final String FLOOR_NOT_FOUND = "Floor not found with id : %s ";
    public static final String ROOM_NOT_FOUND = "Room not found with id : %s ";
    public static final String ORGANIZATION_NOT_FOUND = "Organization not found with id : %s ";
    public static final String BUILDING_NOT_FOUND = "Building not found with id : %s ";
    public static final String DAY_PATTERN = "dd.MM.yyyy";

}
