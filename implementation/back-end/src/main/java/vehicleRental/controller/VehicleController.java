package vehicleRental.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import vehicleRental.model.Vehicle;
import vehicleRental.service.VehicleService;

@RestController
@RequestMapping("/vehicle")
@Validated
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @GetMapping("/model/{model}")
    public ResponseEntity<Vehicle> findByModel(@PathVariable String model) {
        Vehicle newVehicle = this.vehicleService.findByModel(model);
        return ResponseEntity.ok().body(newVehicle);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Vehicle> findById(@PathVariable Long id) {
        Vehicle newVehicle = this.vehicleService.findById(id);
        return ResponseEntity.ok().body(newVehicle);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Vehicle>> findAllByVehicles() {
        List<Vehicle> newVehicle = this.vehicleService.findByAllVehicles();
        return ResponseEntity.ok().body(newVehicle);
    }

}
