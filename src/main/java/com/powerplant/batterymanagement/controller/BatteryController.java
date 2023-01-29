package com.powerplant.batterymanagement.controller;

import com.powerplant.batterymanagement.helper.BatteryHelper;
import com.powerplant.batterymanagement.model.Battery;
import com.powerplant.batterymanagement.service.BatteryService;
import common.ResponseHandler;
import io.swagger.annotations.Contact;
import io.swagger.annotations.ExternalDocs;
import io.swagger.annotations.Info;
import io.swagger.annotations.License;
import io.swagger.annotations.SwaggerDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
public class BatteryController {

  @Autowired
  BatteryService batteryService;

  @PostMapping("/batteries")
  public ResponseEntity<Object> saveBattery(@RequestBody List<Battery> batteryList) {
    try {
      batteryList.stream().forEach(x -> batteryService.saveOrUpdate(x));
      return ResponseHandler.generateResponse("Successfully added " + batteryList.size() + " battery data!", HttpStatus.OK, null);
    } catch (Exception e) {
      return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.BAD_REQUEST, null);
    }
  }

  @GetMapping("/batteries/postcode")
  public ResponseEntity<Object> getBatteriesBetweenPostCode(@RequestParam int startPostCode,
                                                                   @RequestParam int endPostCode) {
    try {
      Map<String, Object> data = new HashMap<String, Object>();

      List<Battery> batteries = batteryService.getBatteriesBetweenPostCodes(startPostCode, endPostCode);
      data.put("batteryNames", batteries.stream().map(x -> x.getName()).toArray());

      data.put("sumBatteryWatts", BatteryHelper.sumBatteryWatts(batteries));
      data.put("averageBatteryWatts", BatteryHelper.averageBatteryWatts(batteries));

      return ResponseHandler.generateResponse("Successfully retrieved data!", HttpStatus.OK, data);
    } catch (Exception e) {
      return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.BAD_REQUEST, null);
    }
  }
}
