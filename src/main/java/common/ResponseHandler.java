package common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ResponseHandler
{
  public static ResponseEntity<Object> generateResponse(String message, HttpStatus status, Object data) {
    Map<String, Object> response = new HashMap<String, Object>();
      response.put("message", message);
      response.put("status", status);
      response.put("data", data);

    return new ResponseEntity<Object>(response,status);
  }
}
