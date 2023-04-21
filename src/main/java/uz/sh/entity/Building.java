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


/**
 * Building class is buildings of Organizations
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Building extends Auditable {

    private String name;

    @ManyToOne
    private Organization organization;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "building")
    private List<Floor> floors;

}
