package com.powerplant.batterymanagement.service;

import com.powerplant.batterymanagement.model.Battery;
import com.powerplant.batterymanagement.repository.BatteryRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BatteryServiceTest
{
  @Mock
  BatteryRepository batteryRepository;

  @InjectMocks
  BatteryService batteryService;

  @Test
  public void getBatteriesBetweenPostCodesTest() {
    List<Battery> batteryList = new ArrayList<Battery>();

    Battery battery = new Battery(2599, "Panasonic", 74000);
    batteryList.add(battery);

    when(batteryRepository.findBatteriesBetweenPostCodes(anyInt(), anyInt())).thenReturn(batteryList);

    List<Battery> batteryListService = batteryService.getBatteriesBetweenPostCodes(2000, 3000);

    assertThat(batteryListService.size()).isSameAs(batteryList.size());
    assertThat(batteryListService.get(0).getName()).isSameAs(batteryList.get(0).getName());
  }
}
