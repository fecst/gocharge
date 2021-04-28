package br.com.gocharg.controller;

import br.com.gocharg.command.CommandContext;
import br.com.gocharg.domain.defaultResponses.FluentResponse;
import br.com.gocharg.dto.EstadoDTO;
import br.com.gocharg.exceptions.BadRequestException;
import br.com.gocharg.mappers.*;
import br.com.gocharg.processor.cidade.BuscaCidadesPorEstadoProcessor;
import br.com.gocharg.processor.estado.*;
import br.com.gocharg.processor.subZonas.BuscaSubZonaPorEstadoProcessor;
import br.com.gocharg.processor.totem.BuscaTotemPorEstadoProcessor;
import br.com.gocharg.processor.zonas.BuscaZonaPorEstadoProcessor;
import br.com.gocharg.validator.EstadoValidator;
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
  @Autowired private BuscaZonaPorEstadoProcessor buscaZonaPorEstadoProcessor;
  @Autowired private BuscaSubZonaPorEstadoProcessor buscaSubZonaPorEstadoProcessor;
  @Autowired private BuscaTotemPorEstadoProcessor buscaTotemPorEstadoProcessor;

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

  @GetMapping("/estados/{id_estado}/totens")
  public ResponseEntity<Object> getTotensPorEstado(@PathVariable("id_estado") String idEstado) {
    CommandContext context = new CommandContext();
    context.put("idEstado", idEstado);

    return ResponseEntity.ok()
        .body(
            FluentResponse.success()
                .data(
                    TotemMapper.INSTANCE.toDTO(buscaTotemPorEstadoProcessor.process(context))));
  }

  @GetMapping("/estados/{id_estado}/zonas")
  public ResponseEntity<Object> getZonasPorEstado(@PathVariable("id_estado") String idEstado) {
    CommandContext context = new CommandContext();
    context.put("idEstado", idEstado);

    return ResponseEntity.ok()
        .body(
            FluentResponse.success()
                .data(ZonaMapper.INSTANCE.toDTO(buscaZonaPorEstadoProcessor.process(context))));
  }

  @GetMapping("/estados/{id_estado}/subZonas")
  public ResponseEntity<Object> getSubZonasPorEstado(@PathVariable("id_estado") String idEstado) {
    CommandContext context = new CommandContext();
    context.put("idEstado", idEstado);

    return ResponseEntity.ok()
        .body(
            FluentResponse.success()
                .data(
                    SubZonaMapper.INSTANCE.toDTO(buscaSubZonaPorEstadoProcessor.process(context))));
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
