package net.metrosystems.demo.car.fleet.management.repositories;

import net.metrosystems.demo.car.fleet.management.domain.Car;
import net.metrosystems.demo.car.fleet.management.utils.PostgreSQLPropertiesRetriever;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseCarFleetDAO {

  private static final String DATABASE_URL = PostgreSQLPropertiesRetriever
          .readProperties().getProperty("db.url");

  private static final String USERNAME = PostgreSQLPropertiesRetriever
          .readProperties().getProperty("db.user");

  private static final String PASSWORD = PostgreSQLPropertiesRetriever
          .readProperties().getProperty("db.passwd");

  public List<Car> getAllFleetCars() {
    List<Car> cars = new ArrayList<>();

    try (Connection con = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
         PreparedStatement pst = con.prepareStatement("SELECT * FROM cars");
         ResultSet rs = pst.executeQuery()) {

      while (rs.next()) {
        Car car = new Car(rs.getString(1), rs.getInt(2), rs.getString(3));
        cars.add(car);
      }

    } catch (SQLException ex) {

      Logger lgr = Logger.getLogger(
              DatabaseCarFleetDAO.class.getName());
      lgr.log(Level.SEVERE, ex.getMessage(), ex);
    }
    return cars;
  }

  public void addCar(Car car) {

    String query = "INSERT INTO cars(car_model, manufacture_year, license_plate, assignee) " +
            "VALUES(?, ?, ?, ?)";

    try (Connection con = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
         PreparedStatement pst = con.prepareStatement(query)) {

      pst.setString(1, car.getCarModel());
      pst.setInt(2, car.getManufactureYear());
      pst.setString(3, car.getLicensePlate());
      pst.setString(4, car.getAssignee());
      pst.executeUpdate();

    } catch (SQLException ex) {

      Logger lgr = Logger.getLogger(DatabaseCarFleetDAO.class.getName());
      lgr.log(Level.SEVERE, ex.getMessage(), ex);
    }
  }

  public void removeCar(String licensePlate) {

    String query = "DELETE from cars where license_plate = ?";

    try (Connection con = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
         PreparedStatement pst = con.prepareStatement(query)) {

      pst.setString(1, licensePlate);
      pst.execute();

    } catch (SQLException ex) {

      Logger lgr = Logger.getLogger(DatabaseCarFleetDAO.class.getName());
      lgr.log(Level.SEVERE, ex.getMessage(), ex);
    }
  }

  public void removeAllCars() {
    String query = "DELETE from cars";

    try (Connection con = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
         PreparedStatement pst = con.prepareStatement(query)) {

      pst.execute();

    } catch (SQLException ex) {

      Logger lgr = Logger.getLogger(DatabaseCarFleetDAO.class.getName());
      lgr.log(Level.SEVERE, ex.getMessage(), ex);
    }
  }

  public void assignCarToEmployee(String employeeName, String licensePlate) {
    String query = "UPDATE cars set assignee = ? where license_plate = ?";

    try (Connection con = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
         PreparedStatement pst = con.prepareStatement(query)) {

      pst.setString(1, employeeName);
      pst.setString(2, licensePlate);
      pst.execute();

    } catch (SQLException ex) {

      Logger lgr = Logger.getLogger(DatabaseCarFleetDAO.class.getName());
      lgr.log(Level.SEVERE, ex.getMessage(), ex);
    }
  }

  private void executeDatabaseOperation(String query, Consumer<PreparedStatement> pstConsumer) {
    try (Connection con = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
         PreparedStatement pst = con.prepareStatement(query)) {
      pstConsumer.accept(pst);
    } catch (SQLException ex) {
      Logger lgr = Logger.getLogger(DatabaseCarFleetDAO.class.getName());
      lgr.log(Level.SEVERE, ex.getMessage(), ex);
    }
  }
}
