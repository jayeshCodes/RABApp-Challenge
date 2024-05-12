package animal.crud.machadolabtest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovementDto {
    private Integer id;
    private String accountCompany;
    private String newMovementReason;
    private String newSpecies;
    private String newOriginAddress;
    private String newOriginCity;
    private String newOriginName;
    private String newOriginPostalCode;
    private String newOriginPremId; // Assuming this is the correct spelling for the field
    private String newOriginState;
    private String newDestinationAddress;
    private String newDestinationCity;
    private String newDestinationName;
    private String newDestinationPostalCode;
    private String newDestinationPremId; // Assuming this is the correct spelling for the field
    private String newDestinationState;
    private Double originLat;
    private Double originLon;
    private Double destinationLat;
    private Double destinationLon;
    private Integer newNumItemsMoved;
    private Date newShipmentsStartDate; // Assuming you have imported Date class

    // public String getNewDestinationPremid() {
    //     return this.newDestinationPremId;
    // }

    // public String getNewOriginPremid() {
    //     return this.newOriginPremId;
    // }
}
