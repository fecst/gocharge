package br.com.gocharge.controller;

import br.com.gocharge.command.CommandContext;
import br.com.gocharge.domain.defaultResponses.FluentResponse;
import br.com.gocharge.processor.ApagaEstadoPorIdProcessor;
import br.com.gocharge.processor.BuscaEstadosProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class EstadoController {

  @Autowired BuscaEstadosProcessor buscaEstadosProcessor;
  @Autowired ApagaEstadoPorIdProcessor apagaEstadoPorIdProcessor;

  @GetMapping("/estados")
  public ResponseEntity<Object> getEstados() {
    return ResponseEntity.ok()
        .body(FluentResponse.success().data(buscaEstadosProcessor.process(null)));
  }

  @GetMapping("/estados/{id_estado}")
  public ResponseEntity<Object> getEstadosPorId(@PathVariable("id_estado") String idEstado) {
    CommandContext context = new CommandContext();
    context.put("idEstado", UUID.fromString(idEstado));

    return ResponseEntity.ok()
        .body(FluentResponse.success().data(buscaEstadosProcessor.process(context)));
  }

  @PostMapping("/estados")
  public ResponseEntity<Object> postEstado() {
    return ResponseEntity.status(HttpStatus.CREATED).body(null);
  }

  @PutMapping("/estados/{id_estado}")
  public ResponseEntity<Object> putEstado(@PathVariable("id_estado") String idEstado) {
    CommandContext context = new CommandContext();
    context.put("idEstado", UUID.fromString(idEstado));

    return ResponseEntity.ok().body(null);
  }

  @DeleteMapping("/estados/{id_estado}")
  public ResponseEntity<Object> deleteEstado(@PathVariable("id_estado") String idEstado) {
    CommandContext context = new CommandContext();
    context.put("idEstado", UUID.fromString(idEstado));

    return ResponseEntity.accepted().body(apagaEstadoPorIdProcessor.process(context));
  }
}
