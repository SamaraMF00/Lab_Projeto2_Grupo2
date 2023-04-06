package vehicleRental.service;

import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vehicleRental.repository.RequestRepository;
import vehicleRental.service.exceptions.ObjectNotFoundException;

import vehicleRental.model.Request;

@Service
public class RequestService {

    @Autowired
    private RequestRepository requestRepository;

    public Request findById(Long id) {
        Optional<Request> request = this.requestRepository.findById(id);

        return request.orElseThrow(() -> new ObjectNotFoundException("Request not found in database"));
    }

    @Transactional
    public Request create(Request newObj) {
        newObj.setId(null);
        return this.requestRepository.save(newObj);
    }

    public Request findByIdCustumer(Long idCustumer) {
        Optional<Request> request = this.requestRepository.findByIdCustomer(idCustumer);
        return request.orElseThrow(() -> new ObjectNotFoundException("Request not found in database"));
    }

}
