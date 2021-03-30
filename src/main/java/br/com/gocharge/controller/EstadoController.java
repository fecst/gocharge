package br.com.gocharge.controller;

import br.com.gocharge.command.CommandContext;
import br.com.gocharge.domain.defaultResponses.FluentResponse;
import br.com.gocharge.dto.EstadoDTO;
import br.com.gocharge.exceptions.BadRequestException;
import br.com.gocharge.mappers.CidadeMapper;
import br.com.gocharge.mappers.EstadoMapper;
import br.com.gocharge.processor.cidade.BuscaCidadesPorEstadoProcessor;
import br.com.gocharge.processor.estado.*;
import br.com.gocharge.validator.EstadoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class EstadoController {

  @Autowired private BuscaEstadosProcessor buscaEstadosProcessor;
  @Autowired private BuscaEstadoPorIdProcessor buscaEstadoPorIdProcessor;
  @Autowired private ApagaEstadoPorIdProcessor apagaEstadoPorIdProcessor;
  @Autowired private CadastraEstadoProcessor cadastraEstadoProcessor;
  @Autowired private AlteraEstadoProcessor alteraEstadoProcessor;
  @Autowired private EstadoValidator estadoValidator;
  @Autowired private BuscaCidadesPorEstadoProcessor buscaCidadesPorEstadoProcessor;

  @GetMapping("/estados")
  public ResponseEntity<Object> getEstados() {
    return ResponseEntity.ok()
        .body(
            FluentResponse.success()
                .data(EstadoMapper.INSTANCE.toDTO(buscaEstadosProcessor.process(null))));
  }

  @GetMapping("/estados/{id_estado}")
  public ResponseEntity<Object> getEstadosPorId(@PathVariable("id_estado") String idEstado) {
    CommandContext context = new CommandContext();
    context.put("idEstado", idEstado);

    return ResponseEntity.ok()
        .body(
            FluentResponse.success()
                .data(EstadoMapper.INSTANCE.toDTO(buscaEstadoPorIdProcessor.process(context))));
  }

  @GetMapping("/estados/{id_estado}/cidades")
  public ResponseEntity<Object> getCidadesPorEstado(@PathVariable("id_estado") String idEstado) {
    CommandContext context = new CommandContext();
    context.put("idEstado", idEstado);

    return ResponseEntity.ok()
        .body(
            FluentResponse.success()
                .data(
                    CidadeMapper.INSTANCE.toDTO(buscaCidadesPorEstadoProcessor.process(context))));
  }

  @PostMapping("/estados")
  public ResponseEntity<Object> postEstado(@RequestBody EstadoDTO estado) {
    estadoValidator.validate(estado).isInvalidThrow(BadRequestException.class);

    CommandContext context = new CommandContext();
    context.put("estado", estado);

    return ResponseEntity.status(HttpStatus.CREATED)
        .body(
            FluentResponse.success()
                .data(EstadoMapper.INSTANCE.toDTO(cadastraEstadoProcessor.process(context))));
  }

  @PutMapping("/estados/{id_estado}")
  public ResponseEntity<Object> putEstado(
      @PathVariable("id_estado") String idEstado, @RequestBody EstadoDTO estado) {
    estadoValidator.validate(estado).isInvalidThrow(BadRequestException.class);

    estado.setId(idEstado);

    CommandContext context = new CommandContext();
    context.put("estado", estado);

    return ResponseEntity.ok()
        .body(
            FluentResponse.success()
                .data(EstadoMapper.INSTANCE.toDTO(alteraEstadoProcessor.process(context))));
  }

  @DeleteMapping("/estados/{id_estado}")
  public ResponseEntity<Object> deleteEstado(@PathVariable("id_estado") String idEstado) {
    CommandContext context = new CommandContext();
    context.put("idEstado", UUID.fromString(idEstado));

    return ResponseEntity.accepted().body(apagaEstadoPorIdProcessor.process(context));
  }
}
