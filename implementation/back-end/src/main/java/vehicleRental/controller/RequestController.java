package vehicleRental.controller;

import java.text.ParseException;
import java.util.List;
import javax.validation.Valid;
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

import vehicleRental.service.RequestService;
import vehicleRental.model.Request;

@RestController
@RequestMapping("/request")
@Validated
public class RequestController {

    @Autowired
    private RequestService requestService;

    @PostMapping
    @Validated
    public ResponseEntity<Request> create(@Valid @RequestBody Request obj) {
        Request newObj = this.requestService.create(obj);
        return ResponseEntity.ok().body(newObj);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Request> findById(@PathVariable Long id) {
        Request obj = this.requestService.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping("/customer/{idUser}")
    public ResponseEntity<Request> findByIdUser(@PathVariable Long idUser) {
        Request obj = this.requestService.findByIdCustumer(idUser);
        return ResponseEntity.ok().body(obj);
    }

}
