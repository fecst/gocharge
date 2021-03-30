package br.com.gocharge.controller;

import br.com.gocharge.command.CommandContext;
import br.com.gocharge.domain.defaultResponses.FluentResponse;
import br.com.gocharge.dto.CidadeDTO;
import br.com.gocharge.exceptions.BadRequestException;
import br.com.gocharge.mappers.CidadeMapper;
import br.com.gocharge.processor.cidade.*;
import br.com.gocharge.validator.CidadeValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class CidadeController {

  @Autowired private BuscaCidadesProcessor buscaCidadesProcessor;
  @Autowired private BuscaCidadePorIdProcessor buscaCidadePorIdProcessor;
  @Autowired private ApagaCidadePorIdProcessor apagaCidadePorIdProcessor;
  @Autowired private CadastraCidadeProcessor cadastraCidadeProcessor;
  @Autowired private AlteraCidadeProcessor alteraCidadeProcessor;
  @Autowired private CidadeValidator cidadeValidator;

  @GetMapping("/cidades")
  public ResponseEntity<Object> getCidades() {
    return ResponseEntity.ok()
        .body(
            FluentResponse.success()
                .data(CidadeMapper.INSTANCE.toDTO(buscaCidadesProcessor.process(null))));
  }

  @GetMapping("/cidades/{id_cidade}")
  public ResponseEntity<Object> getCidadesPorId(@PathVariable("id_cidade") String idCidade) {
    CommandContext context = new CommandContext();
    context.put("idCidade", Integer.valueOf(idCidade));

    return ResponseEntity.ok()
            .body(
                    FluentResponse.success()
                            .data(CidadeMapper.INSTANCE.toDTO(buscaCidadePorIdProcessor.process(context))));
  }

  @PostMapping("/cidades")
  public ResponseEntity<Object> postCidade(@RequestBody CidadeDTO cidade) {
    cidadeValidator.validate(cidade).isInvalidThrow(BadRequestException.class);

    CommandContext context = new CommandContext();
    context.put("cidade", cidade);

    return ResponseEntity.status(HttpStatus.CREATED)
        .body(
            FluentResponse.success()
                .data(CidadeMapper.INSTANCE.toDTO(cadastraCidadeProcessor.process(context))));
  }

  @PutMapping("/cidades/{id_cidade}")
  public ResponseEntity<Object> putCidade(
      @PathVariable("id_cidade") String idCidade, @RequestBody CidadeDTO cidade) {
    cidadeValidator.validate(cidade).isInvalidThrow(BadRequestException.class);

    cidade.setId(idCidade);

    CommandContext context = new CommandContext();
    context.put("cidade", cidade);

    return ResponseEntity.ok()
        .body(
            FluentResponse.success()
                .data(CidadeMapper.INSTANCE.toDTO(alteraCidadeProcessor.process(context))));
  }

  @DeleteMapping("/cidades/{id_cidade}")
  public ResponseEntity<Object> deleteCidade(@PathVariable("id_cidade") String idCidade) {
    CommandContext context = new CommandContext();
    context.put("idCidade", UUID.fromString(idCidade));

    return ResponseEntity.accepted()
        .body(CidadeMapper.INSTANCE.toDTO(apagaCidadePorIdProcessor.process(context)));
  }
}
