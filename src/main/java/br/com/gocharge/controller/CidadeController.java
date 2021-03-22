package br.com.gocharge.controller;

import br.com.gocharge.command.CommandContext;
import br.com.gocharge.domain.Cidade;
import br.com.gocharge.domain.defaultResponses.FluentResponse;
import br.com.gocharge.exceptions.BadRequestException;
import br.com.gocharge.processor.cidade.*;
import br.com.gocharge.validator.CidadeValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class CidadeController {

  @Autowired private BuscaCidadeProcessor buscaCidadeProcessor;
  @Autowired private BuscaCidadePorIdProcessor buscaCidadePorIdProcessor;
  @Autowired private ApagaCidadePorIdProcessor apagaCidadePorIdProcessor;
  @Autowired private CadastraCidadeProcessor cadastraCidadeProcessor;
  @Autowired private AlteraCidadeProcessor alteraCidadeProcessor;
  @Autowired private CidadeValidator cidadeValidator;

  @GetMapping("/cidades")
  public ResponseEntity<Object> getEstados() {
    return ResponseEntity.ok()
        .body(FluentResponse.success().data(buscaCidadeProcessor.process(null)));
  }

  @GetMapping("/cidades/{id_cidade}")
  public ResponseEntity<Object> getEstadosPorId(@PathVariable("id_cidade") String idCidade) {
    CommandContext context = new CommandContext();
    context.put("idCidade", UUID.fromString(idCidade));

    return ResponseEntity.ok()
        .body(FluentResponse.success().data(buscaCidadePorIdProcessor.process(context)));
  }

  @PostMapping("/cidades")
  public ResponseEntity<Object> postCidade(@RequestBody Cidade cidade) {
    cidadeValidator.validate(cidade).isInvalidThrow(BadRequestException.class);

    CommandContext context = new CommandContext();
    context.put("cidade", cidade);

    return ResponseEntity.status(HttpStatus.CREATED)
        .body(FluentResponse.success().data(cadastraCidadeProcessor.process(context)));
  }

  @PutMapping("/cidades/{id_cidade}")
  public ResponseEntity<Object> putCidade(
      @PathVariable("id_cidade") String idCidade, @RequestBody Cidade cidade) {
    cidadeValidator.validate(cidade).isInvalidThrow(BadRequestException.class);

    cidade.setId(UUID.fromString(idCidade));

    CommandContext context = new CommandContext();
    context.put("cidade", cidade);

    return ResponseEntity.ok()
        .body(FluentResponse.success().data(alteraCidadeProcessor.process(context)));
  }

  @DeleteMapping("/cidades/{id_cidade}")
  public ResponseEntity<Object> deleteCidade(@PathVariable("id_cidade") String idCidade) {
    CommandContext context = new CommandContext();
    context.put("idCidade", UUID.fromString(idCidade));

    return ResponseEntity.accepted().body(apagaCidadePorIdProcessor.process(context));
  }
}
