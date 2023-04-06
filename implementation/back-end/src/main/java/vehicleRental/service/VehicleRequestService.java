package vehicleRental.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vehicleRental.model.VehicleRequest;
import vehicleRental.repository.VehicleRequestRepository;
import vehicleRental.service.exceptions.DataBindingViolationException;
import vehicleRental.service.exceptions.ObjectNotFoundException;

@Service
public class VehicleRequestService {

    @Autowired
    private VehicleRequestRepository vehicleRequestRepository;

    public VehicleRequest findById(Long id) {
        Optional<VehicleRequest> vehicleRequest = this.vehicleRequestRepository.findById(id);
        return vehicleRequest.orElseThrow(() -> new ObjectNotFoundException("Vehicle request not found in database"));
    }

    @Transactional
    public VehicleRequest create(VehicleRequest newVehicleRequest) {
        newVehicleRequest.setId(null);
        return this.vehicleRequestRepository.save(newVehicleRequest);
    }

    public void delete(Long id) {
        try {
            VehicleRequest newObj = findById(id);
            this.vehicleRequestRepository.delete(newObj);
        } catch (Exception e) {
            throw new DataBindingViolationException("Entity request depends on another entity");
        }
    }

    public void deleteAllByLoan(Long id) {
        this.vehicleRequestRepository.deleteById(id);
    }

    public List<VehicleRequest> findAllByRequest(Long request_id) {
        List<VehicleRequest> vehicleRequest = this.vehicleRequestRepository.findAllByIdRequest(request_id);
        return vehicleRequest;
    }
}
