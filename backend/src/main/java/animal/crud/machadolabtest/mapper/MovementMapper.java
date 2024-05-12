package animal.crud.machadolabtest.mapper;

import animal.crud.machadolabtest.dto.MovementDto;
import animal.crud.machadolabtest.entity.Movement;

public class MovementMapper {
    public static MovementDto mapToMovementDto(Movement movement){
        return new MovementDto(
            movement.getId(),
            movement.getAccountCompany(),
            movement.getMovementReason(),
            movement.getSpecies(),
            movement.getOriginAddress(),
            movement.getOriginCity(),
            movement.getOriginName(),
            movement.getOriginPostalCode(),
            movement.getOriginPremiseId(),
            movement.getOriginState(),
            movement.getDestinationAddress(),
            movement.getDestinationCity(),
            movement.getDestinationName(),
            movement.getDestinationPostalCode(),
            movement.getDestinationPremiseId(),
            movement.getDestinationState(),
            movement.getOriginLat(),
            movement.getOriginLong(),
            movement.getDestinationLat(),
            movement.getDestinationLong(),
            movement.getNumItemsMoved(),
            movement.getShipmentStartDate()
        );
    }

    public static Movement mapToMovement(MovementDto movementDto){
        return new Movement(
            movementDto.getId(),
            movementDto.getAccountCompany(),
            movementDto.getNewMovementReason(),
            movementDto.getNewSpecies(),
            movementDto.getNewOriginAddress(),
            movementDto.getNewOriginCity(),
            movementDto.getNewOriginName(),
            movementDto.getNewOriginPostalCode(),
            movementDto.getNewOriginPremId(),
            movementDto.getNewOriginState(),
            movementDto.getNewDestinationAddress(),
            movementDto.getNewDestinationCity(),
            movementDto.getNewDestinationName(),
            movementDto.getNewDestinationPostalCode(),
            movementDto.getNewDestinationPremId(),
            movementDto.getNewDestinationState(),
            movementDto.getOriginLat(),
            movementDto.getOriginLon(),
            movementDto.getDestinationLat(),
            movementDto.getDestinationLon(),
            movementDto.getNewNumItemsMoved(),
            movementDto.getNewShipmentsStartDate()
        );
    }
}
