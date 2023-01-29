package com.powerplant.batterymanagement.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.powerplant.batterymanagement.helper.BatteryHelper;
import com.powerplant.batterymanagement.model.Battery;
import common.ResponseHandler;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(BatteryController.class)
public class BatteryControllerTest
{
  @MockBean
  private BatteryController batteryController;

  ObjectMapper mapper = new ObjectMapper();

  @Autowired
  private MockMvc mvc;

  @Test
  public void saveBatteryTest() throws Exception {
    List<Battery> batteryList = new ArrayList<Battery>();
    batteryList.add(new Battery(2599, "Panasonic", 74000));

    String responseMessage = "Successfully added " + batteryList.size() + " battery data!";
    given(batteryController.saveBattery(batteryList)).willReturn(ResponseHandler.generateResponse(responseMessage, HttpStatus.OK, null));

    mvc.perform(post("/batteries")
        .content(mapper.writeValueAsString(batteryList))
                    .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.message").value(responseMessage));
  }

  @Test
  public void getBatteriesBetweenPostCodeTest() throws Exception {
    List<Battery> batteryList = new ArrayList<Battery>();
    batteryList.add(new Battery(2599, "Panasonic", 74000));

    Map<String, Object> data = new HashMap<String, Object>();
    data.put("batteryNames", batteryList.stream().map(x -> x.getName()).toArray());

    data.put("sumBatteryWatts", BatteryHelper.sumBatteryWatts(batteryList));
    data.put("averageBatteryWatts", BatteryHelper.averageBatteryWatts(batteryList));

    String responseMessage = "Successfully retrieved data!";
    given(batteryController.getBatteriesBetweenPostCode(anyInt(), anyInt())).willReturn(ResponseHandler.generateResponse(responseMessage, HttpStatus.OK, data));

    mvc.perform(get("/batteries/postcode")
                    .param("startPostCode","2000")
                    .param("endPostCode","3000")
                    .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.message").value(responseMessage));
  }
}
