package net.metrosystems.demo.car.fleet.management;

import net.metrosystems.demo.car.fleet.management.domain.Car;
import net.metrosystems.demo.car.fleet.management.repositories.DatabaseCarFleetDAO;
import net.metrosystems.demo.car.fleet.management.services.CarFleetManagementService;

public class CarFleetManagementApplication {

  public static void main(String[] args) {

    CarFleetManagementService carFleetManagementService = new CarFleetManagementService(new DatabaseCarFleetDAO());

    Car car1 = new Car("Skoda Rapid", 2015, "IF02VFC");
    Car car2 = new Car("Skoda Rapid", 2015, "IF03VFC");

    carFleetManagementService.addCar(car1);
    carFleetManagementService.addCar(car2);

    carFleetManagementService.assignCarToEmployee("Ciprian Dumitrel", "IF02VFC");

    //carFleetManagementService.removeAllCars();

    carFleetManagementService.getAllFleetCars().forEach(System.out::println);
  }
}
