package uz.sh.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

/**
 * Author: Shoxruh Bekpulatov
 * Time: 4/19/23 5:33 PM
 **/

/**
 * Item class is that equipments are for workers
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Item extends Auditable {

    @Column(length = 80)
    private String itemName;

    @Column(length = 60)
    private String model;

    private BigDecimal price;

    @ManyToOne(fetch = FetchType.LAZY)
    private Complex complex;

    @ManyToOne(fetch = FetchType.LAZY)
    private Room room;

}
