package animal.crud.machadolabtest.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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
@Entity
@Table(name = "movement")
public class Movement {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto gen id
    private int id;

    @Column(name = "account_company")
    private String accountCompany;

    @Column(name = "new_movementreason")
    private String movementReason;

    @Column(name = "new_species")
    private String species;

    @Column(name = "new_originaddress")
    private String originAddress;

    @Column(name = "new_origincity")
    private String originCity;

    @Column(name = "new_originname")
    private String originName;

    @Column(name = "new_originpostalcode")
    private String originPostalCode;

    @Column(name = "new_originpremid")
    private String originPremiseId;

    @Column(name = "new_originstate")
    private String originState;

    @Column(name = "new_destinationaddress")
    private String destinationAddress;

    @Column(name = "new_destinationcity")
    private String destinationCity;

    @Column(name = "new_destinationname")
    private String destinationName;

    @Column(name = "new_destinationpostalcode")
    private String destinationPostalCode;

    @Column(name = "new_destinationpremid")
    private String destinationPremiseId;

    @Column(name = "new_destinationstate")
    private String destinationState;

    @Column(name = "origin_lat")
    private double originLat;

    @Column(name = "origin_lon")
    private double originLong;

    @Column(name = "destination_lat")
    private double destinationLat;

    @Column(name = "destination_long")
    private double destinationLong;

    @Column(name = "new_numitemsmoved")
    private int numItemsMoved;

    @Column(name = "new_shipmentsstartdate")
    private Date shipmentStartDate;
}
