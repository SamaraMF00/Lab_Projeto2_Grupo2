package vehicleRental.service;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vehicleRental.model.Vehicle;

public class VehicleService {

    Vehicle findByVehicleModel(String nome);
    Vehicle findOneByModel(String nome);
    Vehicle findById(Long id);
    List<Vehicle> findByAllVehicles();
    void deleteById(Long id);

    void createVehicle(Vehicle vehicle);
    void editVehicle(Vehicle vehicle);
    void deleteVehicle(String nome);
}
