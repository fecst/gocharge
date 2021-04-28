package br.com.gocharg.controller;

import br.com.gocharg.command.CommandContext;
import br.com.gocharg.domain.defaultResponses.FluentResponse;
import br.com.gocharg.dto.ZonaDTO;
import br.com.gocharg.exceptions.BadRequestException;
import br.com.gocharg.mappers.SubZonaMapper;
import br.com.gocharg.mappers.TotemMapper;
import br.com.gocharg.mappers.ZonaMapper;
import br.com.gocharg.processor.subZonas.BuscaSubZonaPorZonaProcessor;
import br.com.gocharg.processor.totem.BuscaTotemPorZonaProcessor;
import br.com.gocharg.processor.zonas.*;
import br.com.gocharg.validator.ZonaValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class ZonaController {

  @Autowired private BuscaZonasProcessor buscaZonasProcessor;
  @Autowired private BuscaZonaPorIdProcessor buscaZonaPorIdProcessor;
  @Autowired private ApagaZonaPorIdProcessor apagaZonaPorIdProcessor;
  @Autowired private CadastraZonaProcessor cadastraZonaProcessor;
  @Autowired private AlteraZonaProcessor alteraZonaProcessor;
  @Autowired private ZonaValidator zonaValidator;
  @Autowired private BuscaSubZonaPorZonaProcessor buscaSubZonaPorZonaProcessor;
  @Autowired private BuscaTotemPorZonaProcessor buscaTotemPorZonaProcessor;

  @GetMapping("/zonas")
  public ResponseEntity<Object> getZonas() {
    return ResponseEntity.ok()
        .body(
            FluentResponse.success()
                .data(ZonaMapper.INSTANCE.toDTO(buscaZonasProcessor.process(null))));
  }

  @GetMapping("/zonas/{id_zona}")
  public ResponseEntity<Object> getZonasPorId(@PathVariable("id_zona") String idZona) {
    CommandContext context = new CommandContext();
    context.put("idZona", UUID.fromString(idZona));

    return ResponseEntity.ok()
        .body(
            FluentResponse.success()
                .data(ZonaMapper.INSTANCE.toDTO(buscaZonaPorIdProcessor.process(context))));
  }

  @GetMapping("/zonas/{id_zona}/subZonas")
  public ResponseEntity<Object> getSubZonasPorZona(@PathVariable("id_zona") String idZona) {
    CommandContext context = new CommandContext();
    context.put("idZona", UUID.fromString(idZona));

    return ResponseEntity.ok()
        .body(
            FluentResponse.success()
                .data(SubZonaMapper.INSTANCE.toDTO(buscaSubZonaPorZonaProcessor.process(context))));
  }

  @GetMapping("/zonas/{id_zona}/totens")
  public ResponseEntity<Object> getTotensPorZona(@PathVariable("id_zona") String idZona) {
    CommandContext context = new CommandContext();
    context.put("idZona", UUID.fromString(idZona));

    return ResponseEntity.ok()
        .body(
            FluentResponse.success()
                .data(TotemMapper.INSTANCE.toDTO(buscaTotemPorZonaProcessor.process(context))));
  }

  @PostMapping("/zonas")
  public ResponseEntity<Object> postZona(@RequestBody ZonaDTO zona) {
    zonaValidator.validate(zona).isInvalidThrow(BadRequestException.class);

    CommandContext context = new CommandContext();
    context.put("zona", zona);

    return ResponseEntity.status(HttpStatus.CREATED)
        .body(
            FluentResponse.success()
                .data(ZonaMapper.INSTANCE.toDTO(cadastraZonaProcessor.process(context))));
  }

  @PutMapping("/zonas/{id_zona}")
  public ResponseEntity<Object> putZona(
      @PathVariable("id_zona") String idZona, @RequestBody ZonaDTO zona) {
    zonaValidator.validate(zona).isInvalidThrow(BadRequestException.class);

    zona.setId(idZona);

    CommandContext context = new CommandContext();
    context.put("zona", zona);

    return ResponseEntity.ok()
        .body(
            FluentResponse.success()
                .data(ZonaMapper.INSTANCE.toDTO(alteraZonaProcessor.process(context))));
  }

  @DeleteMapping("/zonas/{id_zona}")
  public ResponseEntity<Object> deleteZona(@PathVariable("id_zona") String idZona) {
    CommandContext context = new CommandContext();
    context.put("idZona", UUID.fromString(idZona));

    return ResponseEntity.accepted()
        .body(ZonaMapper.INSTANCE.toDTO(apagaZonaPorIdProcessor.process(context)));
  }

  @PostMapping("/test")
  public ResponseEntity<Object> test(@RequestBody String message) {
    System.out.println(message);
    return ResponseEntity.ok().body(message);
  }
}
