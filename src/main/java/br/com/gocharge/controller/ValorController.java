package br.com.gocharge.controller;

import br.com.gocharge.command.CommandContext;
import br.com.gocharge.domain.defaultResponses.FluentResponse;
import br.com.gocharge.dto.ValorDTO;
import br.com.gocharge.exceptions.BadRequestException;
import br.com.gocharge.mappers.ValorMapper;
import br.com.gocharge.processor.valor.*;
import br.com.gocharge.validator.ValorValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class ValorController {

  @Autowired private BuscaValoresProcessor buscaValoresProcessor;
  @Autowired private BuscaValorPorIdProcessor buscaValorPorIdProcessor;
  @Autowired private ApagaValorPorIdProcessor apagaValorPorIdProcessor;
  @Autowired private CadastraValorProcessor cadastraValorProcessor;
  @Autowired private AlteraValorProcessor alteraValorProcessor;
  @Autowired private ValorValidator valorValidator;

  @GetMapping("/valores")
  public ResponseEntity<Object> getBandeiras() {
    return ResponseEntity.ok()
        .body(
            FluentResponse.success()
                .data(ValorMapper.INSTANCE.toDTO(buscaValoresProcessor.process(null))));
  }

  @GetMapping("/valores/{id_valor}")
  public ResponseEntity<Object> getBandeirasPorId(@PathVariable("id_valor") String idValor) {
    CommandContext context = new CommandContext();
    context.put("idValor", UUID.fromString(idValor));

    return ResponseEntity.ok()
        .body(
            FluentResponse.success()
                .data(ValorMapper.INSTANCE.toDTO(buscaValorPorIdProcessor.process(context))));
  }

  @PostMapping("/valores")
  public ResponseEntity<Object> postBandeira(@RequestBody ValorDTO valor) {
    valorValidator.validate(valor).isInvalidThrow(BadRequestException.class);

    CommandContext context = new CommandContext();
    context.put("valor", valor);

    return ResponseEntity.status(HttpStatus.CREATED)
        .body(
            FluentResponse.success()
                .data(ValorMapper.INSTANCE.toDTO(cadastraValorProcessor.process(context))));
  }

  @PutMapping("/valores/{id_valor}")
  public ResponseEntity<Object> putBandeira(
      @PathVariable("id_valor") String idValor, @RequestBody ValorDTO valor) {
    valorValidator.validate(valor).isInvalidThrow(BadRequestException.class);

    valor.setId(idValor);

    CommandContext context = new CommandContext();
    context.put("valor", valor);

    return ResponseEntity.ok()
        .body(
            FluentResponse.success()
                .data(ValorMapper.INSTANCE.toDTO(alteraValorProcessor.process(context))));
  }

  @DeleteMapping("/valores/{id_valor}")
  public ResponseEntity<Object> deleteBandeira(@PathVariable("id_valor") String idValor) {
    CommandContext context = new CommandContext();
    context.put("idValor", UUID.fromString(idValor));

    return ResponseEntity.accepted()
        .body(ValorMapper.INSTANCE.toDTO(apagaValorPorIdProcessor.process(context)));
  }
}
