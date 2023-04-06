package vehicleRental.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import vehicleRental.model.Request;
import vehicleRental.model.Vehicle;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {

    @Query(value = "select * from request where request.customer_id = :idUser", nativeQuery = true)
    Optional<Request> findByIdCustomer(@Param("idUser") Long idUser);

}
