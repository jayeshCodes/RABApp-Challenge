package animal.crud.machadolabtest.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.List;
import org.springframework.web.bind.annotation.PutMapping;
import animal.crud.machadolabtest.dto.MovementDto;
import animal.crud.machadolabtest.service.MovementService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/movements")
public class MovementController {
    private MovementService movementService;

    @PostMapping("/add")
    public ResponseEntity<MovementDto> createMovement(@RequestBody MovementDto movementDto){
        MovementDto savedMovement = movementService.createMovement(movementDto);
        return new ResponseEntity<>(savedMovement, HttpStatus.CREATED);
    }
    
    @GetMapping("{id}")
    public ResponseEntity<MovementDto> getMovementByID(@PathVariable("id") Integer id) {
        MovementDto movementDto = movementService.getMovementById(id);
        return ResponseEntity.ok(movementDto);
    }

    @GetMapping("/all")
    public ResponseEntity<List<MovementDto>> getAllMovements(){
        List<MovementDto> allMovements = movementService.getAllMovements();
        return ResponseEntity.ok(allMovements);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<MovementDto> updateMovement(@PathVariable("id") Integer id,@RequestBody MovementDto updatedMovement){
        MovementDto movementDto = movementService.updateMovement(id, updatedMovement);
        return ResponseEntity.ok(movementDto);
    }
    
    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteMovement(@PathVariable("id") Integer id){
        movementService.deleteMovement(id);
        return ResponseEntity.ok("Movement deleted Successfully");
    }
}
