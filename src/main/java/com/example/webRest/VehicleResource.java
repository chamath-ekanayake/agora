package com.example.webRest;


import com.example.domain.User;
import com.example.domain.Vehicle;
import com.example.service.VehicleService;
import com.example.webRest.util.HeaderUtil;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
@Api(value = "Vehicle Resource REST Endpoint", description = "Shows the Vehicle info")
public class VehicleResource {

    private final Logger log = LoggerFactory.getLogger(BookingResource.class);


    public VehicleResource() {

    }

    @Autowired
    VehicleService vehicleService;

    @PostMapping("/vehicle")
    public ResponseEntity<Vehicle> getInquiryInsert(@Valid @RequestBody Vehicle vehicle) throws URISyntaxException, ParseException, Exception {
        log.info("REST request to save vehicle : {}", vehicle);
        try {


            Vehicle result = vehicleService.getPostVehicle(vehicle);

            return ResponseEntity.created(new URI("/api/vehicle/" + result.getContactNo()))
                    .headers(HeaderUtil.createEntityCreationAlert(vehicle.getDriverName(), result.getEmail()))
                    .body(result);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
    @GetMapping("/vehicle/{inquiryId}")
    public ResponseEntity<List> getAvailableVehicleSearch(@PathVariable("inquiryId") final String inquiryId)throws IOException  {
        log.info("REST recontactNoquest to inquiryId  : {}", inquiryId);

        List driverName = null;
        try {
            driverName= vehicleService.findAvailableVehicle(inquiryId);
            return new ResponseEntity<>(driverName, HttpStatus.OK);
        }catch (Exception ex){
            ex.getMessage();
        }

   //     log.info("REST  to Return List  : {}", driverName);

        return new ResponseEntity<>(driverName, HttpStatus.OK);
    }

    @GetMapping("/vehicle-all")
    public ResponseEntity<List<Vehicle>> getAll () throws IOException {
        List<Vehicle> vehicleList=null;
        try {

            vehicleList = vehicleService.findAll();
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        return new ResponseEntity<>(vehicleList, HttpStatus.OK);
    }


    @GetMapping("/driverName/{driverName}")
    public ResponseEntity<List<Vehicle>> getDriverNameSearch(@PathVariable("driverName") final String driverName)throws IOException  {
        log.info("REST Vehicle to driverName  : {}", driverName);

        List<Vehicle> searchVehicles=null;
        try {
            searchVehicles = vehicleService.getDriverNameSearch(driverName);
        }
        catch (Exception ex){ex.getMessage();}
        return new ResponseEntity<List<Vehicle>>(searchVehicles, HttpStatus.OK);
    }

    @GetMapping("/availableVehicle/{startDate}/{endDate}/{vehicleType}")
    public ResponseEntity<List<Vehicle>> getAvailableVehicle(@PathVariable("startDate") final Date startDate, @PathVariable("endDate") final Date endDate, @PathVariable("vehicleType") final String vehicleType)throws IOException  {
        log.info("REST Vehicle to getAvailableVehicle  : {}", startDate);

        List<Vehicle> searchVehicles=null;
        try {
            searchVehicles = vehicleService.getAvailableVehicle(startDate,endDate,vehicleType);
            log.info("REST Vehicle to ReturnList  : {}", searchVehicles);
        }
        catch (Exception ex){ex.getMessage();}
        return new ResponseEntity<List<Vehicle>>(searchVehicles, HttpStatus.OK);
    }
}
