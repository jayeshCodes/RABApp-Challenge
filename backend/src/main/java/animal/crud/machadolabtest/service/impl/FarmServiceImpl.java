package animal.crud.machadolabtest.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import animal.crud.machadolabtest.dto.FarmDto;
import animal.crud.machadolabtest.entity.Farm;
import animal.crud.machadolabtest.exception.ResourceNotFoundException;
import animal.crud.machadolabtest.mapper.FarmMapper;
import animal.crud.machadolabtest.repository.FarmRepository;
import animal.crud.machadolabtest.service.FarmService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class FarmServiceImpl implements FarmService {
    private FarmRepository farmRepository;

    @Override
    public FarmDto createFarm(FarmDto farmDto) {
        Farm farm = FarmMapper.mapToFarm(farmDto);
        Farm savedFarm = farmRepository.save(farm);
        return FarmMapper.mapToFarmDto(savedFarm);
    }

    @Override
    public FarmDto getFarmById(String premiseid) {
        Farm farm = farmRepository.findById(premiseid)
                .orElseThrow(() -> new ResourceNotFoundException("Invalid ID: " + premiseid));
        return FarmMapper.mapToFarmDto(farm);
    }

    @Override
    public List<FarmDto> getAllFarms() {
        List<Farm> allFarms = farmRepository.findAll();
        return allFarms.stream().map((farm) -> FarmMapper.mapToFarmDto(farm)).collect(Collectors.toList());
    }

    @Override
    public FarmDto updateFarm(String premiseid, FarmDto updatedFarm) {
        Farm farm = farmRepository.findById(premiseid)
                .orElseThrow(() -> new ResourceNotFoundException("Invalid ID: " + premiseid));
        farm.setTotalAnimal(updatedFarm.getTotal_animal());

        Farm updatedFarmObj = farmRepository.save(farm);

        return FarmMapper.mapToFarmDto(updatedFarmObj);
    }

    @Override
    public void deleteFarm(String premiseid) {
        Farm farm = farmRepository.findById(premiseid)
                .orElseThrow(() -> new ResourceNotFoundException("Invalid ID: " + premiseid));

        farmRepository.deleteById(premiseid);
    }
}
