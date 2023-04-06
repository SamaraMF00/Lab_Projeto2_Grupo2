package vehicleRental.model;

import javax.persistence.*;

import lombok.Data;
import lombok.ToString;

@Entity
@Data
@ToString
@Table(name = "vehicle")

public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

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
        this.id = vehicleId;
        this.licensePlate = licensePlate;
        this.model = model;
        this.brand = brand;
        this.year = year;
        this.owner = owner;
    }

}
