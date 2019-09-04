package net.metrosystems.demo.car.fleet.management.services;

import net.metrosystems.demo.car.fleet.management.domain.Car;
import net.metrosystems.demo.car.fleet.management.repositories.DatabaseCarFleetDAO;

import java.util.List;

public class CarFleetManagementService {

  private DatabaseCarFleetDAO carFleetDAO = new DatabaseCarFleetDAO();

  public List<Car> getAllFleetCars() {
    return carFleetDAO.getAllFleetCars();
  }

  public void addCar(Car car) {
    carFleetDAO.addCar(car);
    sendEmail("A new car was added to the fleet with license plate: " + car.getLicensePlate());
  }

  public void removeCar(String licensePlate) {
    carFleetDAO.removeCar(licensePlate);
    sendEmail("A new car was removed from the fleet with license plate: " + licensePlate);
  }

  public void assignCarToEmployee(String employeeName, String licensePlate) {
    carFleetDAO.assignCarToEmployee(employeeName, licensePlate);
    sendEmail("Car with license plate " + licensePlate + " was assigned to " + employeeName);
  }

  private void sendEmail(String message) {
    System.out.println(message);
  }

  public void removeAllCars() {
    carFleetDAO.removeAllCars();
  }
}
