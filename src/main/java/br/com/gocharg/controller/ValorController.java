package br.com.gocharg.controller;

import br.com.gocharg.command.CommandContext;
import br.com.gocharg.domain.defaultResponses.FluentResponse;
import br.com.gocharg.dto.ValorDTO;
import br.com.gocharg.exceptions.BadRequestException;
import br.com.gocharg.mappers.TotemMapper;
import br.com.gocharg.mappers.ValorMapper;
import br.com.gocharg.processor.totem.BuscaTotemPorValorProcessor;
import br.com.gocharg.processor.valor.*;
import br.com.gocharg.validator.ValorValidator;
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
  @Autowired private BuscaTotemPorValorProcessor buscaTotemPorValorProcessor;

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

  @GetMapping("/valores/{id_valor}/totens")
  public ResponseEntity<Object> getTotemPorValor(@PathVariable("id_valor") String idValor) {
    CommandContext context = new CommandContext();
    context.put("idValor", UUID.fromString(idValor));

    return ResponseEntity.ok()
        .body(
            FluentResponse.success()
                .data(TotemMapper.INSTANCE.toDTO(buscaTotemPorValorProcessor.process(context))));
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
