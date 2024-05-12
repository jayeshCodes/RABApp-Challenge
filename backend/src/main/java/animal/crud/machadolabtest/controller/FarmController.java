package animal.crud.machadolabtest.controller;

import org.springframework.web.bind.annotation.RestController;

import animal.crud.machadolabtest.dto.FarmDto;
import animal.crud.machadolabtest.service.FarmService;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;
import org.springframework.web.bind.annotation.PutMapping;



@AllArgsConstructor
@RestController
@RequestMapping("/api/farms")
public class FarmController {
    private FarmService farmService;

    // Build Add Farm Rest API
    @PostMapping("/add")
    public ResponseEntity<FarmDto> createFarm(@RequestBody FarmDto farmDto){
        FarmDto savedFarm = farmService.createFarm(farmDto);
        return new ResponseEntity<>(savedFarm, HttpStatus.CREATED);
    }
    
    // Build Get Farm Rest API
    @GetMapping("{id}")
    public ResponseEntity<FarmDto> getFarmByID(@PathVariable("id") String presmiseid) {
        FarmDto farmDto = farmService.getFarmById(presmiseid);
        return ResponseEntity.ok(farmDto);
    }

    // Build Get All Farms REST API
    @GetMapping("/all")
    public ResponseEntity<List<FarmDto>> getAllFarms(){
        List<FarmDto> allFarms = farmService.getAllFarms();
        return ResponseEntity.ok(allFarms);
    }

    // Build UpdateFarm REST API
    @PutMapping("update/{id}")
    public ResponseEntity<FarmDto> updateFarm(@PathVariable("id") String premiseid,@RequestBody FarmDto updatedFarm){
        FarmDto farmDto = farmService.updateFarm(premiseid, updatedFarm);
        return ResponseEntity.ok(farmDto);
    }
    
    // Build Delete Farm RestAPI
    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteFarm(@PathVariable("id") String premiseid){
        farmService.deleteFarm(premiseid);
        return ResponseEntity.ok("Farm deleted Successfully");
    }
}
