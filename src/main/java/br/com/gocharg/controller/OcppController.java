package br.com.gocharg.controller;

import br.com.gocharg.command.CommandContext;
import br.com.gocharg.processor.ocpp.OcppProcessor;
import br.com.gocharg.processor.ocpp.down.request.RemoteStartTransactionRequestProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class OcppController {

  @Autowired private OcppProcessor ocppProcessor;
  @Autowired private RemoteStartTransactionRequestProcessor remoteStartTransactionRequestProcessor;

  @PostMapping("/ocpp")
  public ResponseEntity<Object> receive(@RequestBody Object[] ocppMessage) {
    CommandContext context = new CommandContext();
    context.put("ocppMessage", ocppMessage);

    return ResponseEntity.status(HttpStatus.OK).body(ocppProcessor.process(context));
  }

  @GetMapping("/charge_point/start/{apelido_totem}")
  public ResponseEntity<Object> start(@PathVariable("apelido_totem") String apelidoTotem) {
    CommandContext context = new CommandContext();
    context.put("apelidoTotem", apelidoTotem);

    return ResponseEntity.status(HttpStatus.OK)
        .body(remoteStartTransactionRequestProcessor.process(context));
  }
}
