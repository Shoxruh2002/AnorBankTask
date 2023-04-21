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
 * Time: 4/19/23 5:34 PM
 **/
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Complex extends Auditable {

    private String complexName;

    @ManyToOne
    private Room room;

    @OneToOne
    private AuthUser authUser;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "complex")
    private List<Item> items;

}
