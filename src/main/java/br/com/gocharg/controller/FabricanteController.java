package br.com.gocharg.controller;

import br.com.gocharg.command.CommandContext;
import br.com.gocharg.domain.defaultResponses.FluentResponse;
import br.com.gocharg.dto.FabricanteDTO;
import br.com.gocharg.exceptions.BadRequestException;
import br.com.gocharg.mappers.FabricanteMapper;
import br.com.gocharg.processor.fabricante.*;
import br.com.gocharg.validator.FabricanteValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class FabricanteController {

  @Autowired private BuscaFabricantesProcessor buscaFabricantesProcessor;
  @Autowired private BuscaFabricantePorIdProcessor buscaFabricantePorIdProcessor;
  @Autowired private ApagaFabricantePorIdProcessor apagaFabricantePorIdProcessor;
  @Autowired private CadastraFabricanteProcessor cadastraFabricanteProcessor;
  @Autowired private AlteraFabricanteProcessor alteraFabricanteProcessor;
  @Autowired private FabricanteValidator fabricanteValidator;

  @GetMapping("/fabricantes")
  public ResponseEntity<Object> getFabricantes() {
    return ResponseEntity.ok()
        .body(
            FluentResponse.success()
                .data(FabricanteMapper.INSTANCE.toDTO(buscaFabricantesProcessor.process(null))));
  }

  @GetMapping("/fabricantes/{id_fabricante}")
  public ResponseEntity<Object> getBandeirasPorId(
      @PathVariable("id_fabricante") String idFabricante) {
    CommandContext context = new CommandContext();
    context.put("idFabricante", UUID.fromString(idFabricante));

    return ResponseEntity.ok()
        .body(
            FluentResponse.success()
                .data(
                    FabricanteMapper.INSTANCE.toDTO(
                        buscaFabricantePorIdProcessor.process(context))));
  }

  @PostMapping("/fabricantes")
  public ResponseEntity<Object> postBandeira(@RequestBody FabricanteDTO fabricante) {
    fabricanteValidator.validate(fabricante).isInvalidThrow(BadRequestException.class);

    CommandContext context = new CommandContext();
    context.put("fabricante", fabricante);

    return ResponseEntity.status(HttpStatus.CREATED)
        .body(
            FluentResponse.success()
                .data(
                    FabricanteMapper.INSTANCE.toDTO(cadastraFabricanteProcessor.process(context))));
  }

  @PutMapping("/fabricantes/{id_fabricante}")
  public ResponseEntity<Object> putBandeira(
      @PathVariable("id_fabricante") String idFabricante, @RequestBody FabricanteDTO fabricante) {
    fabricanteValidator.validate(fabricante).isInvalidThrow(BadRequestException.class);

    fabricante.setId(idFabricante);

    CommandContext context = new CommandContext();
    context.put("fabricante", fabricante);

    return ResponseEntity.ok()
        .body(
            FluentResponse.success()
                .data(FabricanteMapper.INSTANCE.toDTO(alteraFabricanteProcessor.process(context))));
  }

  @DeleteMapping("/fabricantes/{id_fabricante}")
  public ResponseEntity<Object> deleteBandeira(@PathVariable("id_fabricante") String idFabricante) {
    CommandContext context = new CommandContext();
    context.put("idFabricante", UUID.fromString(idFabricante));

    return ResponseEntity.accepted()
        .body(FabricanteMapper.INSTANCE.toDTO(apagaFabricantePorIdProcessor.process(context)));
  }
}
