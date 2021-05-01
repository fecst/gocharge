package br.com.gocharg.controller;

import br.com.gocharg.ocpp.json.request.OcppRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class StatusController {

  @PostMapping("/status")
  public ResponseEntity<Object> postStatus(@RequestBody Object[] message) {
    System.out.println(message);

    OcppRequest ocppRequest = new OcppRequest();

    ocppRequest.setOperation(Integer.valueOf(message[0].toString()));
    ocppRequest.setUniqueID(message[1].toString());
    ocppRequest.setAction(message[2].toString());
    ocppRequest.setPayload(message[3].toString());

    return ResponseEntity.status(HttpStatus.OK).body(Map.of("statuscode", "ok"));
  }
}
