package com.powerplant.batterymanagement.helper;

import com.powerplant.batterymanagement.model.Battery;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;

import java.util.Collections;
import java.util.List;

public class BatteryHelper
{
  public static double sumBatteryWatts(List<Battery> batteryList) {
    double sum = batteryList.stream().mapToDouble(x -> x.getWattage()).sum();
    return sum;
  }

  public static double averageBatteryWatts(List<Battery> batteryList) {
    double average = batteryList.stream().mapToDouble(x -> x.getWattage()).average().getAsDouble();
    return average;
  }

  public static ApiInfo apiInfo() {
    return new ApiInfo(
        "Battery Management API",
        "API that performs CRUD operations of the PowerPlant Batteries",
        "v0.0.1-SNAPSHOT",
        "www.powerplant.au/batteries/termsofservice",
        new Contact("PowerPlant", "www.powerplant.au", "cpviola@powerplant.au"),
        "@PowerPlantLicense", "www.powerplant.au/batteries/license", Collections.emptyList());
  }
}
