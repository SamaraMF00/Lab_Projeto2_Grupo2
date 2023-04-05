package vehicleRental.service;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vehicleRental.repository.RequestRepository;
import vehicleRental.service.exceptions.DataBindingViolationException;
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

    @Transactional
    public Request update(Request obj) {
        Request newObj = findById(obj.getId());
        newObj.setDueDate(obj.getDueDate());
        return this.requestRepository.save(newObj);
    }

    public void delete(Long id) {
        try {
            Request obj = findById(id);
            this.requestRepository.delete(obj);
        } catch (Exception e) {
            throw new DataBindingViolationException("Entity request depends on another entity");
        }
    }

    public List<Request> findAllByDueDate(String dueDate) {
        List<Request> requests = this.requestRepository.findAllByDueDate(dueDate);
        return requests; 
    }

    public Request findByIdCustumer(Long idCustumer) {
        Optional<Request> request = this.requestRepository.findByIdCustumer(idCustumer);
        return request.orElseThrow(() -> new ObjectNotFoundException("Request not found in database"));
    }

}
