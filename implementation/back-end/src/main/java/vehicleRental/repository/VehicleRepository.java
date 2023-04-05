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

    Vehicle findOneByLivroId(Long livroId);

    List<Vehicle> findAllByLocalizacaoOrderByNomeLivro(String localizacao);

    List<Vehicle> findAllByAutorLivroOrderByNomeLivro(String autorLivro);

    List<Vehicle> findAllByDeletedAtIsNullOrderByNomeLivro();

    Optional<Vehicle> findByVehicleModel(String nome);

    List<Vehicle> findAllByNomeLivroAndDisponibilidadeContainingIgnoreCaseOrderByNomeLivro(String nomeLivro,
            String disponibilidade);

    @Query(value = "select * from livro where not exists (select * from requestVehicle where requestVehicle.vehicle_id = vehicle.id", nativeQuery = true)
    List<Vehicle> findByAllVehicles();

    @Query(value = "select * from livro l where l.nome = :nome limit 1", nativeQuery = true)
    Vehicle findOneByNome(@Param("nome") String nome);

    @Modifying
    @Transactional
    @Query(value = "delete from livro where livro_id = :livro_id", nativeQuery = true)
    void deleteVehicleById(Long livro_id);

}