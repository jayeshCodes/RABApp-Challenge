package animal.crud.machadolabtest.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import animal.crud.machadolabtest.dto.MovementDto;
import animal.crud.machadolabtest.entity.Farm;
import animal.crud.machadolabtest.entity.Movement;
import animal.crud.machadolabtest.exception.ResourceNotFoundException;
import animal.crud.machadolabtest.mapper.MovementMapper;
import animal.crud.machadolabtest.repository.FarmRepository;
import animal.crud.machadolabtest.repository.MovementRepository;
import animal.crud.machadolabtest.service.MovementService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MovementServiceImpl implements MovementService {
    private MovementRepository movementRepository;
    private FarmRepository farmRepository;

    @Override
    public MovementDto createMovement(MovementDto movementDto) {
        // Map movement DTO to entity
        Movement movement = MovementMapper.mapToMovement(movementDto);
    
        // Fetch details of origin and destination farms from the movement DTO
        String originPremId = movementDto.getNewOriginPremId();
        String destinationPremId = movementDto.getNewDestinationPremId();
        int numItemsMoved = movementDto.getNewNumItemsMoved();
    
        // Fetch origin and destination farms from the database
        Farm originFarm = farmRepository.findById(originPremId)
                .orElseThrow(() -> new ResourceNotFoundException("Invalid farm ID: " + originPremId));
        Farm destinationFarm = farmRepository.findById(destinationPremId)
                .orElseThrow(() -> new ResourceNotFoundException("Invalid farm ID: " + destinationPremId));
    
        // Set the coordinates from the farms to the movement entity
        movement.setOriginLat(originFarm.getFarm_latitude());
        movement.setOriginLong(originFarm.getFarm_longitude());
        movement.setDestinationLat(destinationFarm.getFarm_latitude());
        movement.setDestinationLong(destinationFarm.getFarm_longitude());
    
        // Update the farms
        updateFarmsForMovement(originPremId, destinationPremId, numItemsMoved);
    
        // Save the movement
        Movement savedMovement = movementRepository.save(movement);
    
        // Map saved movement entity back to DTO and return
        return MovementMapper.mapToMovementDto(savedMovement);
    }
    // Implement other service methods similarly
    @Override
    public MovementDto getMovementById(Integer id) {
        Movement movement = movementRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Invalid ID: " + id));
        return MovementMapper.mapToMovementDto(movement);
    }

    @Override
    public List<MovementDto> getAllMovements() {
        List<Movement> allMovements = movementRepository.findAll();
        return allMovements.stream().map((movement) -> MovementMapper.mapToMovementDto(movement))
                .collect(Collectors.toList());
    }

    @Override
    public MovementDto updateMovement(Integer id, MovementDto updatedMovement) {
        Movement movement = movementRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Invalid ID: " + id));

        // Fetch details of origin and destination farms from the movement DTO
        String originPremId = updatedMovement.getNewOriginPremId();
        String destinationPremId = updatedMovement.getNewDestinationPremId();

        // Update the farms
        updateFarmsForMovement(movement.getOriginPremiseId(), destinationPremId, movement.getNumItemsMoved());

        // Update movement details
        movement.setAccountCompany(updatedMovement.getAccountCompany());
        movement.setMovementReason(updatedMovement.getNewMovementReason());
        movement.setShipmentStartDate(updatedMovement.getNewShipmentsStartDate());
        movement.setSpecies(updatedMovement.getNewSpecies());
        movement.setNumItemsMoved(updatedMovement.getNewNumItemsMoved());

        // Save the updated movement
        Movement updatedMovementobj = movementRepository.save(movement);

        return MovementMapper.mapToMovementDto(updatedMovementobj);
    }

    @Override
    public void deleteMovement(Integer id) {
        Movement movement = movementRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Invalid ID: " + id));

        // Fetch details of origin and destination farms from the movement
        String originPremId = movement.getOriginPremiseId();
        String destinationPremId = movement.getDestinationPremiseId();
        int numItemsMoved = movement.getNumItemsMoved();

        // Update the farms
        updateFarmsForMovement(originPremId, destinationPremId, -numItemsMoved);

        // Delete the movement
        movementRepository.deleteById(id);
    }

    // Helper method to update farms based on movement
    private void updateFarmsForMovement(String originPremId, String destinationPremId, int numItemsMoved) {
        // Fetch origin and destination farms from the database
        Farm originFarm = farmRepository.findById(originPremId)
                .orElseThrow(() -> new ResourceNotFoundException("Invalid farm ID: " + originPremId));
        Farm destinationFarm = farmRepository.findById(destinationPremId)
                .orElseThrow(() -> new ResourceNotFoundException("Invalid farm ID: " + destinationPremId));

        // Update the number of items moved
        originFarm.setTotalAnimal(originFarm.getTotalAnimal() - numItemsMoved);
        destinationFarm.setTotalAnimal(destinationFarm.getTotalAnimal() + numItemsMoved);

        // Save the updated farms
        farmRepository.save(originFarm);
        farmRepository.save(destinationFarm);
    }
}
