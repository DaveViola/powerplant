package com.powerplant.batterymanagement.service;

import com.powerplant.batterymanagement.model.Battery;
import com.powerplant.batterymanagement.repository.BatteryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BatteryService {

  @Autowired
  BatteryRepository batteryRepository;

  public void saveOrUpdate(Battery battery) {
    batteryRepository.save(battery);
  }

  public List<Battery> getBatteriesBetweenPostCodes(int startPostCode, int endPostCode) {
    if(endPostCode < startPostCode) {
      throw new IllegalArgumentException("Start PostCode must be less than or equal to End PostCode");
    }
    return batteryRepository.findBatteriesBetweenPostCodes(startPostCode, endPostCode);
  }
}
