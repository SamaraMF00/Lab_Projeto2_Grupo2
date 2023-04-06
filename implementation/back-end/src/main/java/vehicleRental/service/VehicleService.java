package vehicleRental.service;

import vehicleRental.model.*;
import vehicleRental.repository.VehicleRepository;
import vehicleRental.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    public Vehicle findByModel(String model) {
        Vehicle Vehicle = this.vehicleRepository.findByModel(model).get();
        return Vehicle;
    }

    public Vehicle findById(Long id) {
        Optional<Vehicle> Vehicle = this.vehicleRepository.findById(id);
        return Vehicle.orElseThrow(() -> new ObjectNotFoundException("Vehicle not found in database"));
    }

    public List<Vehicle> findByAllVehicles() {
        return this.vehicleRepository.findByAllVehicles();
    }

}
