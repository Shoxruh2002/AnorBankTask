package uz.sh.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * Author: Shoxruh Bekpulatov
 * Time: 4/19/23 5:33 PM
 **/
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Floor extends Auditable {

    private Integer floorNumber;

    private Boolean isDown;

    @ManyToOne
    private Building building;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "floor")
    private List<Room> rooms;

}
