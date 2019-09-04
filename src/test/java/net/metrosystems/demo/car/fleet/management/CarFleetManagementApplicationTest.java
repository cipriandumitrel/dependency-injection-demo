package net.metrosystems.demo.car.fleet.management;

import net.metrosystems.demo.car.fleet.management.domain.Car;
import net.metrosystems.demo.car.fleet.management.services.CarFleetManagementService;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CarFleetManagementApplicationTest {

  private CarFleetManagementService carFleetManagementService;

  @Before
  public void init() {
    carFleetManagementService = new CarFleetManagementService();
  }

  @Test
  public void testCarFleetSize() {
    carFleetManagementService.addCar(new Car("Mazda 6", 2012, "IF30AAA"));
    carFleetManagementService.addCar(new Car("Skoda Octavia", 2012, "IF31AAA"));
    assertEquals(carFleetManagementService.getAllFleetCars().size(), 2);
  }

  @Test
  public void removingCarFromFleetShouldDecreaseFleetSize() {
    carFleetManagementService.addCar(new Car("Renault Megane", 2013, "IF32AAA"));
    carFleetManagementService.addCar(new Car("Skoda Octavia", 2014, "IF33AAA"));
    carFleetManagementService.removeCar("IF33AAA");
    assertEquals(carFleetManagementService.getAllFleetCars().size(), 1);
  }

  @After
  public void destroy() {
    carFleetManagementService.removeAllCars();
  }
}
