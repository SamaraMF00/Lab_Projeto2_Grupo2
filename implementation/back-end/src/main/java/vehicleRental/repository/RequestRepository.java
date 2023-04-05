package vehicleRental.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vehicleRental.model.Request;

@Repository
public class RequestRepository {

    @Query(value = "select * from request where due_date like concat('%', :dueDate, '%')", nativeQuery = true)
    List<Request> findAllByDueDate(@Param("dueDate") String dueDate);

    @Query(value = "select * from request where request.reader_id = :idCustumer and not exists (select * from devolution where devolution.request_id = request.id) limit 1", nativeQuery = true)
    Optional<Request> findByIdCustumer(@Param("idCustumer") Long idCustumer);

}
