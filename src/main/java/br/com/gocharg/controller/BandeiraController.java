package br.com.gocharg.controller;

import br.com.gocharg.command.CommandContext;
import br.com.gocharg.domain.defaultResponses.FluentResponse;
import br.com.gocharg.dto.BandeiraDTO;
import br.com.gocharg.exceptions.BadRequestException;
import br.com.gocharg.mappers.BandeiraMapper;
import br.com.gocharg.mappers.ValorMapper;
import br.com.gocharg.processor.bandeira.*;
import br.com.gocharg.processor.valor.BuscaValorPorBandeiraProcessor;
import br.com.gocharg.validator.BandeiraValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class BandeiraController {

  @Autowired private BuscaBandeirasProcessor buscaBandeirasProcessor;
  @Autowired private BuscaBandeiraPorIdProcessor buscaBandeiraPorIdProcessor;
  @Autowired private ApagaBandeiraPorIdProcessor apagaBandeiraPorIdProcessor;
  @Autowired private CadastraBandeiraProcessor cadastraBandeiraProcessor;
  @Autowired private AlteraBandeiraProcessor alteraBandeiraProcessor;
  @Autowired private BandeiraValidator bandeiraValidator;
  @Autowired private BuscaValorPorBandeiraProcessor buscaValorPorBandeiraProcessor;

  @GetMapping("/bandeiras")
  public ResponseEntity<Object> getBandeiras() {
    return ResponseEntity.ok()
        .body(
            FluentResponse.success()
                .data(BandeiraMapper.INSTANCE.toDTO(buscaBandeirasProcessor.process(null))));
  }

  @GetMapping("/bandeiras/{id_bandeira}")
  public ResponseEntity<Object> getBandeirasPorId(@PathVariable("id_bandeira") String idBandeira) {
    CommandContext context = new CommandContext();
    context.put("idBandeira", UUID.fromString(idBandeira));

    return ResponseEntity.ok()
        .body(
            FluentResponse.success()
                .data(BandeiraMapper.INSTANCE.toDTO(buscaBandeiraPorIdProcessor.process(context))));
  }

  @GetMapping("/bandeiras/{id_bandeira}/valores")
  public ResponseEntity<Object> getValoresPorBandeira(
      @PathVariable("id_bandeira") String idBandeira) {
    CommandContext context = new CommandContext();
    context.put("idBandeira", UUID.fromString(idBandeira));

    return ResponseEntity.ok()
        .body(
            FluentResponse.success()
                .data(ValorMapper.INSTANCE.toDTO(buscaValorPorBandeiraProcessor.process(context))));
  }

  @PostMapping("/bandeiras")
  public ResponseEntity<Object> postBandeira(@RequestBody BandeiraDTO bandeira) {
    bandeiraValidator.validate(bandeira).isInvalidThrow(BadRequestException.class);

    CommandContext context = new CommandContext();
    context.put("bandeira", bandeira);

    return ResponseEntity.status(HttpStatus.CREATED)
        .body(
            FluentResponse.success()
                .data(BandeiraMapper.INSTANCE.toDTO(cadastraBandeiraProcessor.process(context))));
  }

  @PutMapping("/bandeiras/{id_bandeira}")
  public ResponseEntity<Object> putBandeira(
      @PathVariable("id_bandeira") String idBandeira, @RequestBody BandeiraDTO bandeira) {
    bandeiraValidator.validate(bandeira).isInvalidThrow(BadRequestException.class);

    bandeira.setId(idBandeira);

    CommandContext context = new CommandContext();
    context.put("bandeira", bandeira);

    return ResponseEntity.ok()
        .body(
            FluentResponse.success()
                .data(BandeiraMapper.INSTANCE.toDTO(alteraBandeiraProcessor.process(context))));
  }

  @DeleteMapping("/bandeiras/{id_bandeira}")
  public ResponseEntity<Object> deleteBandeira(@PathVariable("id_bandeira") String idBandeira) {
    CommandContext context = new CommandContext();
    context.put("idBandeira", UUID.fromString(idBandeira));

    return ResponseEntity.accepted()
        .body(BandeiraMapper.INSTANCE.toDTO(apagaBandeiraPorIdProcessor.process(context)));
  }
}
