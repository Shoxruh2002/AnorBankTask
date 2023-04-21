package uz.sh.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Author: Shoxruh Bekpulatov
 * Time: 4/19/23 5:33 PM
 **/

/**
 * Room class, workers and equipments are located
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Room extends Auditable {

    @Column(length = 50)
    private String roomName;

    @Column(length = 15)
    private String roomNumber;

    @ManyToOne
    private Floor floor;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "room")
    private List<Complex> complexes;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "room")
    private List<Item> items;
}
