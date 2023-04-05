package vehicleRental.controller;

import java.net.URI;
import java.text.ParseException;
import java.util.List;
import javax.validation.Valid;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import vehicleRental.model.Vehicle;
import vehicleRental.repository.VehicleRepository;
import vehicleRental.service.VehicleService;
import vehicleRental.service.RequestService;

public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private RequestService requestService; //verificar se é a chamada do request


    @GetMapping("/model/{model}")
    public ResponseEntity<Vehicle> findByModel(@PathVariable String model) {
        Vehicle newVehicle = this.vehicleService.findByVehicleModel(model);
        return ResponseEntity.ok().body(newVehicle);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Vehicle> findById(@PathVariable Long id) {
        Vehicle newVehicle = this.vehicleService.findById(id);
        return ResponseEntity.ok().body(newVehicle);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Vehicle>> findAllByVehicles() {
        List<Vehicle> newVehicle = this.vehicleService.findByAllVehicles();
        return ResponseEntity.ok().body(newVehicle);
    }

    private URI getPatchId(Long id) {
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
    }

    @PostMapping(path = "/create/{quantidade}")
    @Validated
    public ResponseEntity<Void> create(@Valid @RequestBody Vehicle vehicle, @PathVariable int quantidade) {
        Vehicle newVehicle = null;

        for (int i = 0; i < quantidade; i++) {
            newVehicle = new Vehicle(vehicle.getVehicleId(), vehicle.getLicensePlate(), vehicle.getModel(),
                    vehicle.getBrand(), vehicle.getYear(), vehicle.getOwner());
            vehicleRepository.save(newVehicle);
        }

        if (newVehicle != null)
            return ResponseEntity.created(getPatchId(newVehicle.getVehicleId())).build();
        else
            return null;
    }

    @PutMapping(path = "/update/{model}/{quantidade}")
    public ResponseEntity<Void> updateVehicle(@PathVariable String model, @PathVariable int quantidade) {
        String model2 = model.replaceAll("-"," ");
        Vehicle vehicle = vehicleService.findOneByModel(model2);
        return create(vehicle, quantidade);
    }

    //deletar - todos
    @DeleteMapping(path = "/delete/{model}")
    public void deleteVehicle(@PathVariable String model) {
        String model2 = model.replaceAll("-"," ");
        while(vehicleService.findOneByModel(model2) != null){
            Vehicle vehicle = vehicleService.findOneByModel(model2);
            vehicleService.deleteById(vehicle.getVehicleId());
        };
    }
    //deletar - apenas um
    @DeleteMapping(path = "/deleteOne/{model}")
    public void deleteOneVehicle(@PathVariable String model) {
        String model2 = model.replaceAll("-"," ");
        Vehicle vehicle = vehicleService.findOneByModel(model2);
        vehicleService.deleteById(vehicle.getVehicleId());
    }

    // deletar - escolher quantidade
    @DeleteMapping(path = "/delete/{model}/{quantidade}")
    public void deleteVehicleQtd(@PathVariable String model, @PathVariable int quantidade) {
        String model2 = model.replaceAll("-"," ");
        for(int i = 0; i < quantidade;i++){
            if(vehicleService.findOneByModel(model2) != null){
                Vehicle vehicle = vehicleService.findOneByModel(model2);
                vehicleService.deleteById(vehicle.getVehicleId());
            }
        }
    }

    @GetMapping("/date/dateIni={inclusionDateInit}/dateEnd={inclusionDateEnd}")
    public ResponseEntity<Double> findAllByInclusionDate(@PathVariable String inclusionDateInit,
            @PathVariable String inclusionDateEnd) throws ParseException {
        Double perc = this.vehicleService.percentageAllowancesPerMonth(inclusionDateInit, inclusionDateEnd);
        return ResponseEntity.ok().body(perc);
    }

}
