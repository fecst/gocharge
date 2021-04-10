package br.com.gocharge.controller;

import br.com.gocharge.command.CommandContext;
import br.com.gocharge.domain.defaultResponses.FluentResponse;
import br.com.gocharge.dto.VeiculoDTO;
import br.com.gocharge.exceptions.BadRequestException;
import br.com.gocharge.mappers.VeiculoMapper;
import br.com.gocharge.mappers.ValorMapper;
import br.com.gocharge.processor.veiculo.*;
import br.com.gocharge.validator.VeiculoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class VeiculoController {

  @Autowired private BuscaVeiculosProcessor buscaVeiculosProcessor;
  @Autowired private BuscaVeiculoPorIdProcessor buscaVeiculoPorIdProcessor;
  @Autowired private ApagaVeiculoPorIdProcessor apagaVeiculoPorIdProcessor;
  @Autowired private CadastraVeiculoProcessor cadastraVeiculoProcessor;
  @Autowired private AlteraVeiculoProcessor alteraVeiculoProcessor;
  @Autowired private VeiculoValidator veiculoValidator;

  @GetMapping("/veiculos")
  public ResponseEntity<Object> getVeiculos() {
    return ResponseEntity.ok()
        .body(
            FluentResponse.success()
                .data(VeiculoMapper.INSTANCE.toDTO(buscaVeiculosProcessor.process(null))));
  }

  @GetMapping("/veiculos/{id_veiculo}")
  public ResponseEntity<Object> getVeiculosPorId(@PathVariable("id_veiculo") String idVeiculo) {
    CommandContext context = new CommandContext();
    context.put("idVeiculo", UUID.fromString(idVeiculo));

    return ResponseEntity.ok()
        .body(
            FluentResponse.success()
                .data(VeiculoMapper.INSTANCE.toDTO(buscaVeiculoPorIdProcessor.process(context))));
  }

  @PostMapping("/veiculos")
  public ResponseEntity<Object> postVeiculo(@RequestBody VeiculoDTO veiculo) {
    veiculoValidator.validate(veiculo).isInvalidThrow(BadRequestException.class);

    CommandContext context = new CommandContext();
    context.put("veiculo", veiculo);

    return ResponseEntity.status(HttpStatus.CREATED)
        .body(
            FluentResponse.success()
                .data(VeiculoMapper.INSTANCE.toDTO(cadastraVeiculoProcessor.process(context))));
  }

  @PutMapping("/veiculos/{id_veiculo}")
  public ResponseEntity<Object> putVeiculo(
      @PathVariable("id_veiculo") String idVeiculo, @RequestBody VeiculoDTO veiculo) {
    veiculoValidator.validate(veiculo).isInvalidThrow(BadRequestException.class);

    veiculo.setId(idVeiculo);

    CommandContext context = new CommandContext();
    context.put("veiculo", veiculo);

    return ResponseEntity.ok()
        .body(
            FluentResponse.success()
                .data(VeiculoMapper.INSTANCE.toDTO(alteraVeiculoProcessor.process(context))));
  }

  @DeleteMapping("/veiculos/{id_veiculo}")
  public ResponseEntity<Object> deleteVeiculo(@PathVariable("id_veiculo") String idVeiculo) {
    CommandContext context = new CommandContext();
    context.put("idVeiculo", UUID.fromString(idVeiculo));

    return ResponseEntity.accepted()
        .body(VeiculoMapper.INSTANCE.toDTO(apagaVeiculoPorIdProcessor.process(context)));
  }
}
