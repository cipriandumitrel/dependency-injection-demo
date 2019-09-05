package net.metrosystems.demo.car.fleet.management.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PostgreSQLPropertiesRetriever {

  public static Properties readProperties() {

    Properties props = new Properties();
    Path myPath = Paths.get("src/main/resources/database.properties");

    try {
      BufferedReader bf = Files.newBufferedReader(myPath,
              StandardCharsets.UTF_8);

      props.load(bf);
    } catch (IOException ex) {
      Logger.getLogger(PostgreSQLPropertiesRetriever.class.getName()).log(
              Level.SEVERE, null, ex);
    }

    return props;
  }
}
