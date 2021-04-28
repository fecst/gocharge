package br.com.gocharg.controller;

import br.com.gocharg.command.CommandContext;
import br.com.gocharg.domain.defaultResponses.FluentResponse;
import br.com.gocharg.dto.TotemDTO;
import br.com.gocharg.exceptions.BadRequestException;
import br.com.gocharg.mappers.TotemMapper;
import br.com.gocharg.processor.totem.*;
import br.com.gocharg.validator.TotemValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class TotemController {

  @Autowired private BuscaTotensProcessor buscaTotensProcessor;
  @Autowired private BuscaTotemPorIdProcessor buscaTotemPorIdProcessor;
  @Autowired private ApagaTotemPorIdProcessor apagaTotemPorIdProcessor;
  @Autowired private CadastraTotemProcessor cadastraTotemProcessor;
  @Autowired private AlteraTotemProcessor alteraTotemProcessor;
  @Autowired private TotemValidator totemValidator;
  @Autowired private BuscaTotemPorStatusProcessor buscaTotemPorStatusProcessor;

  @GetMapping("/totens")
  public ResponseEntity<Object> getTotens() {
    return ResponseEntity.ok()
        .body(
            FluentResponse.success()
                .data(TotemMapper.INSTANCE.toDTO(buscaTotensProcessor.process(null))));
  }

  @GetMapping("/totens/disponiveis")
  public ResponseEntity<Object> getTotensDisponiveis() {
    return ResponseEntity.ok()
        .body(
            FluentResponse.success()
                .data(TotemMapper.INSTANCE.toDTO(buscaTotemPorStatusProcessor.process(null))));
  }

  @GetMapping("/totens/{id_totem}")
  public ResponseEntity<Object> getTotemPorId(@PathVariable("id_totem") String idTotem) {
    CommandContext context = new CommandContext();
    context.put("idTotem", UUID.fromString(idTotem));

    return ResponseEntity.ok()
        .body(
            FluentResponse.success()
                .data(TotemMapper.INSTANCE.toDTO(buscaTotemPorIdProcessor.process(context))));
  }

  @PostMapping("/totens")
  public ResponseEntity<Object> postTotem(@RequestBody TotemDTO totem) {
    totemValidator.validate(totem).isInvalidThrow(BadRequestException.class);

    CommandContext context = new CommandContext();
    context.put("totem", totem);

    return ResponseEntity.status(HttpStatus.CREATED)
        .body(
            FluentResponse.success()
                .data(TotemMapper.INSTANCE.toDTO(cadastraTotemProcessor.process(context))));
  }

  @PutMapping("/totens/{id_totem}")
  public ResponseEntity<Object> putTotem(
      @PathVariable("id_totem") String idTotem, @RequestBody TotemDTO totem) {
    totemValidator.validate(totem).isInvalidThrow(BadRequestException.class);

    totem.setId(UUID.fromString(idTotem));

    CommandContext context = new CommandContext();
    context.put("totem", totem);

    return ResponseEntity.ok()
        .body(
            FluentResponse.success()
                .data(TotemMapper.INSTANCE.toDTO(alteraTotemProcessor.process(context))));
  }

  @DeleteMapping("/totens/{id_totem}")
  public ResponseEntity<Object> deleteTotem(@PathVariable("id_totem") String idTotem) {
    CommandContext context = new CommandContext();
    context.put("idTotem", UUID.fromString(idTotem));

    return ResponseEntity.accepted()
        .body(TotemMapper.INSTANCE.toDTO(apagaTotemPorIdProcessor.process(context)));
  }
}
