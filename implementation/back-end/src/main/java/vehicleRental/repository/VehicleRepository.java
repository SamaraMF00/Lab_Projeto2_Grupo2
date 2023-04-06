package vehicleRental.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vehicleRental.model.Vehicle;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    Vehicle findOneById(Long id);

    Optional<Vehicle> findByModel(String nome);

    @Query(value = "select * from vehicle", nativeQuery = true)
    List<Vehicle> findByAllVehicles();
}