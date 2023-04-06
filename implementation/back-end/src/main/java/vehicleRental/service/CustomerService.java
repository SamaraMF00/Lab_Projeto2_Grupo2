package vehicleRental.service;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vehicleRental.model.Customer;
import vehicleRental.repository.CustomerRepository;
import vehicleRental.service.exceptions.DataBindingViolationException;
import vehicleRental.service.exceptions.ObjectNotFoundException;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Customer findById(Long id) {
        Optional<Customer> customer = this.customerRepository.findById(id);

        return customer.orElseThrow(() -> new ObjectNotFoundException("Customer not found in database"));
    }

    public Customer findByCpf(String cpf) {
        Optional<Customer> customer = this.customerRepository.findByCpf(cpf);

        return customer.orElseThrow(() -> new ObjectNotFoundException("Customer not found in database"));
    }

    @Transactional
    public Customer create(Customer newObj) {
        newObj.setId(null);
        return this.customerRepository.save(newObj);
    }

    @Transactional
    public Customer update(Customer obj) {
        return this.customerRepository.save(obj);
    }

    public List<Customer> findByAll() {
        return this.customerRepository.findByAll();
    }

    public void delete(Long id) {
        try {
            Customer obj = findById(id);
            this.customerRepository.delete(obj);
        } catch (Exception e) {
            throw new DataBindingViolationException("Entity loan depends on another entity");
        }
    }
}
