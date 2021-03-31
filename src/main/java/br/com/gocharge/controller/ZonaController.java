package br.com.gocharge.controller;

import br.com.gocharge.command.CommandContext;
import br.com.gocharge.domain.defaultResponses.FluentResponse;
import br.com.gocharge.dto.ZonaDTO;
import br.com.gocharge.exceptions.BadRequestException;
import br.com.gocharge.mappers.ZonaMapper;
import br.com.gocharge.processor.zonas.*;
import br.com.gocharge.validator.ZonaValidator;
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
}
