package vehicleRental.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vehicleRental.model.VehicleRequest;


public class VehicleRequestRepository {

    @Query(value = "select * from vehicleRequest where request_id = :request_id", nativeQuery = true)
    List<VehicleRequest> findAllByIdRequest(@Param("request_id") Long request_id);
    
}
