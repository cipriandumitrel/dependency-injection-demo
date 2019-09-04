package net.metrosystems.demo.car.fleet.management.repositories;

import net.metrosystems.demo.car.fleet.management.domain.Car;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryCarFleetDAO {

  private static Map<String, Car> carsByLicensePlate = new ConcurrentHashMap<>();

  public List<Car> getAllFleetCars() {
    return new ArrayList<>(carsByLicensePlate.values());
  }

  public void addCar(Car car) {
    carsByLicensePlate.putIfAbsent(car.getLicensePlate(), car);
  }

  public void removeCar(String licensePlate) {
    carsByLicensePlate.remove(licensePlate);
  }

  public void assignCarToEmployee(String employeeName, String licensePlate) {
    carsByLicensePlate.get(licensePlate).setAssignee("Ciprian Dumitrel");
  }

  public void removeAllCars() {
    carsByLicensePlate.clear();
  }
}
