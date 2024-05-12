package animal.crud.machadolabtest.service;

import animal.crud.machadolabtest.dto.MovementDto;
import java.util.List;

public interface MovementService {
    MovementDto createMovement(MovementDto movementDto);

    MovementDto getMovementById(Integer id);

    List<MovementDto> getAllMovements();

    MovementDto updateMovement(Integer id, MovementDto updatedMovement);

    void deleteMovement(Integer id);
}
