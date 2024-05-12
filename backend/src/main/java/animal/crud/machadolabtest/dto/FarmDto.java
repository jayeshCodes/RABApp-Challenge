package animal.crud.machadolabtest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FarmDto {
    private String premiseid;
    private int total_animal;
    private String farm_company;
    private double farm_latitude;
    private double farm_longitude;
}
