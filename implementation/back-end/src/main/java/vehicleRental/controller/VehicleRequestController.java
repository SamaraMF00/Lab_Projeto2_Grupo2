package vehicleRental.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import vehicleRental.model.VehicleRequest;
import vehicleRental.service.VehicleRequestService;

@RestController
@RequestMapping("/vehicleRequest")
@Validated

public class VehicleRequestController {

    @Autowired
    private VehicleRequestService vehicleRequestService;

    private URI getPatchId(Long id) {
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
    }

    @PostMapping
    @Validated
    public ResponseEntity<Void> create(@Valid @RequestBody VehicleRequest obj) {
        VehicleRequest newVehicleRequest = this.vehicleRequestService.create(obj);
        return ResponseEntity.created(getPatchId(newVehicleRequest.getId())).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehicleRequest> findById(@PathVariable Long id) {
        VehicleRequest newVehicleRequest = this.vehicleRequestService.findById(id);
        return ResponseEntity.ok().body(newVehicleRequest);
    }

    @GetMapping("/requestId/{request_id}")
    public ResponseEntity<List<VehicleRequest>> findAllByLoan(@PathVariable Long request_id) {
        List<VehicleRequest> list = this.vehicleRequestService.findAllByRequest(request_id);
        return ResponseEntity.ok().body(list);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.vehicleRequestService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
