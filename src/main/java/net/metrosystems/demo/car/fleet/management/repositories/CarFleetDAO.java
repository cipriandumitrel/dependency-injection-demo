package net.metrosystems.demo.car.fleet.management.repositories;

import net.metrosystems.demo.car.fleet.management.domain.Car;

import java.util.List;

public interface CarFleetDAO {

  List<Car> getAllFleetCars();

  void addCar(Car car);

  void removeCar(String licensePlate);

  void removeAllCars();

  void assignCarToEmployee(String employeeName, String licensePlate);
}
