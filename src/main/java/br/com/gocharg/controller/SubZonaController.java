package br.com.gocharg.controller;

import br.com.gocharg.command.CommandContext;
import br.com.gocharg.domain.defaultResponses.FluentResponse;
import br.com.gocharg.dto.SubZonaDTO;
import br.com.gocharg.exceptions.BadRequestException;
import br.com.gocharg.mappers.SubZonaMapper;
import br.com.gocharg.mappers.TotemMapper;
import br.com.gocharg.processor.subZonas.*;
import br.com.gocharg.processor.totem.BuscaTotemPorSubZonaProcessor;
import br.com.gocharg.validator.SubZonaValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class SubZonaController {

  @Autowired private BuscaSubZonasProcessor buscaSubZonasProcessor;
  @Autowired private BuscaSubZonaPorIdProcessor buscaSubZonaPorIdProcessor;
  @Autowired private ApagaSubZonaPorIdProcessor apagaSubZonaPorIdProcessor;
  @Autowired private CadastraSubZonaProcessor cadastraSubZonaProcessor;
  @Autowired private AlteraSubZonaProcessor alteraSubZonaProcessor;
  @Autowired private SubZonaValidator subZonaValidator;
  @Autowired private BuscaTotemPorSubZonaProcessor buscaTotemPorSubZonaProcessor;

  @GetMapping("/subZonas")
  public ResponseEntity<Object> getSubZonas() {
    return ResponseEntity.ok()
        .body(
            FluentResponse.success()
                .data(SubZonaMapper.INSTANCE.toDTO(buscaSubZonasProcessor.process(null))));
  }

  @GetMapping("/subZonas/{id_subZona}")
  public ResponseEntity<Object> getSubZonaPorId(@PathVariable("id_subZona") String idSubZona) {
    CommandContext context = new CommandContext();
    context.put("idSubZona", UUID.fromString(idSubZona));

    return ResponseEntity.ok()
        .body(
            FluentResponse.success()
                .data(SubZonaMapper.INSTANCE.toDTO(buscaSubZonaPorIdProcessor.process(context))));
  }

  @GetMapping("/subZonas/{id_subZona}/totens")
  public ResponseEntity<Object> getTotensPorSubZona(@PathVariable("id_subZona") String idSubZona) {
    CommandContext context = new CommandContext();
    context.put("idSubZona", UUID.fromString(idSubZona));

    return ResponseEntity.ok()
        .body(
            FluentResponse.success()
                .data(TotemMapper.INSTANCE.toDTO(buscaTotemPorSubZonaProcessor.process(context))));
  }

  @PostMapping("/subZonas")
  public ResponseEntity<Object> postSubZona(@RequestBody SubZonaDTO subZona) {
    subZonaValidator.validate(subZona).isInvalidThrow(BadRequestException.class);

    CommandContext context = new CommandContext();
    context.put("subZona", subZona);

    return ResponseEntity.status(HttpStatus.CREATED)
        .body(
            FluentResponse.success()
                .data(SubZonaMapper.INSTANCE.toDTO(cadastraSubZonaProcessor.process(context))));
  }

  @PutMapping("/subZonas/{id_subZona}")
  public ResponseEntity<Object> putSubZona(
      @PathVariable("id_subZona") String idsubZona, @RequestBody SubZonaDTO subZona) {
    subZonaValidator.validate(subZona).isInvalidThrow(BadRequestException.class);

    subZona.setId(idsubZona);

    CommandContext context = new CommandContext();
    context.put("subZona", subZona);

    return ResponseEntity.ok()
        .body(
            FluentResponse.success()
                .data(SubZonaMapper.INSTANCE.toDTO(alteraSubZonaProcessor.process(context))));
  }

  @DeleteMapping("/subZonas/{id_subZona}")
  public ResponseEntity<Object> deleteSubZona(@PathVariable("id_subZona") String idsubZona) {
    CommandContext context = new CommandContext();
    context.put("idSubZona", UUID.fromString(idsubZona));

    return ResponseEntity.accepted()
        .body(SubZonaMapper.INSTANCE.toDTO(apagaSubZonaPorIdProcessor.process(context)));
  }
}
