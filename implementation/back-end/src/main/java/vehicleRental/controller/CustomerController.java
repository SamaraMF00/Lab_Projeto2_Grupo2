package vehicleRental.controller;

import java.util.List;

import javax.validation.Valid;
import vehicleRental.service.CustomerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vehicleRental.model.Customer;

@RestController
@RequestMapping("/customer")
@Validated
public class CustomerController {

    @Autowired
    private CustomerService CustomerService;

    @PostMapping
    @Validated
    public ResponseEntity<Void> create(@Valid @RequestBody Customer obj) {
        this.CustomerService.create(obj);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<Customer> findByCpf(@PathVariable String cpf) {
        Customer obj = this.CustomerService.findByCpf(cpf);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Customer>> findByAll() {
        List<Customer> list = this.CustomerService.findByAll();
        return ResponseEntity.ok().body(list);
    }

    @PutMapping
    @Valid
    public ResponseEntity<Void> update(@Valid @RequestBody Customer obj) {
        this.CustomerService.update(obj);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.CustomerService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
