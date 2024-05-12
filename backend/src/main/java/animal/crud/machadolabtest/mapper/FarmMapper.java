package animal.crud.machadolabtest.mapper;

import animal.crud.machadolabtest.dto.FarmDto;
import animal.crud.machadolabtest.entity.Farm;

public class FarmMapper {

    public static FarmDto mapToFarmDto(Farm farm){
        return new FarmDto(
            farm.getPremiseid(),
            farm.getTotalAnimal(),
            farm.getFarm_company(),
            farm.getFarm_latitude(),
            farm.getFarm_longitude()
        );
    }

    public static Farm mapToFarm(FarmDto farmDto){
        return new Farm(
            farmDto.getPremiseid(),
            farmDto.getTotal_animal(),
            farmDto.getFarm_company(),
            farmDto.getFarm_latitude(),
            farmDto.getFarm_longitude()
        );
    }
}
