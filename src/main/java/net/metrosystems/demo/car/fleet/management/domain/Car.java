package net.metrosystems.demo.car.fleet.management.domain;

import lombok.Data;
import lombok.NonNull;

@Data
public class Car {

  @NonNull
  private String carModel;

  @NonNull
  private Integer manufactureYear;

  @NonNull
  private String licensePlate;

  private String assignee;
}
