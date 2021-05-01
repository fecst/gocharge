package br.com.gocharg.controller;

import br.com.gocharg.command.CommandContext;
import br.com.gocharg.processor.ocpp.ProcessaRequisicaoOcpp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class OcppController {

  @Autowired private ProcessaRequisicaoOcpp processaRequisicaoOcpp;

  @PostMapping("/ocpp")
  public ResponseEntity<Object> postStatus(@RequestBody Object[] ocppMessage) {
    CommandContext context = new CommandContext();
    context.put("ocppMessage", ocppMessage);
    System.out.println(ocppMessage);

    return ResponseEntity.status(HttpStatus.OK).body(processaRequisicaoOcpp.process(context));
  }
}
