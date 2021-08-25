package com.example.service.daoService;

import com.example.domain.User;
import com.example.domain.Vehicle;

import java.util.List;

public interface VehicleDaoService {
    void insertVehicle(Vehicle vehicle);
    List<Vehicle> getFindAll();
    List<Vehicle> getAvailableVehicle();
    Vehicle selectedVehicle(String driverName);
   // Page<Vehicle> select(Pageable pageable);
   List<Vehicle> isDriverNameSearch(String driverName);
    List<Vehicle> getAllVehicleForType(String vehicletype);
}
