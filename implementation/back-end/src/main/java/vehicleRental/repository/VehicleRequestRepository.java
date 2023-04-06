package vehicleRental.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import vehicleRental.model.Vehicle;
import vehicleRental.model.VehicleRequest;

@Repository
public interface VehicleRequestRepository extends JpaRepository<VehicleRequest, Long> {

    @Query(value = "select * from request_vehicle where request_id = :request_id", nativeQuery = true)
    List<VehicleRequest> findAllByIdRequest(@Param("request_id") Long Request_id);

}
