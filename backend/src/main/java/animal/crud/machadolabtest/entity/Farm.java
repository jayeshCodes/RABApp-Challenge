package animal.crud.machadolabtest.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "farm")
public class Farm {
    @Id
    @Column(name = "premiseid")
    private String premiseid;
    @Column(name="total_animal")
    private int totalAnimal;
    @Column(name = "farm_company")
    private String farm_company;
    @Column(name="farm_latitude")
    private double farm_latitude;
    @Column(name="farm_longitude")
    private double farm_longitude;
}
