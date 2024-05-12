package animal.crud.machadolabtest.service;


import animal.crud.machadolabtest.dto.FarmDto;
import java.util.List;

public interface FarmService {
    FarmDto createFarm(FarmDto farmDto);

    FarmDto getFarmById(String premiseid);

    List<FarmDto> getAllFarms();

    FarmDto updateFarm(String premiseid, FarmDto updatedFarm);

    void deleteFarm(String premiseid);
}
