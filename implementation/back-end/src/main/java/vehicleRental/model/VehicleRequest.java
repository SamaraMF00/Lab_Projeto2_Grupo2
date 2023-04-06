package vehicleRental.model;

import javax.persistence.*;

import lombok.Data;
import lombok.ToString;

@Entity
@ToString
@Data
@Table(name = "requestVehicle")

public class VehicleRequest {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    @Column(name = "request_id")
    private Long requestId;

}
