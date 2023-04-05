package vehicleRental.model;

import javax.persistence.*;
import lombok.ToString;

@Entity
@ToString
@Table(name = "vehicle")

public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long vehicleId;

    @Column(name = "licensePlate")
    private String licensePlate;

    @Column(name = "model")
    private String model;

    @Column(name = "brand")
    private String brand;

    @Column(name = "year")
    private String year;

    @Column(name = "owner")
    private String owner;

    public Vehicle() {
    }

    public Vehicle(Long vehicleId, String licensePlate, String model, String brand, String year, String owner) {
        this.vehicleId = vehicleId;
        this.licensePlate = licensePlate;
        this.model = model;
        this.brand = brand;
        this.year = year;
        this.owner = owner;
    }

}
