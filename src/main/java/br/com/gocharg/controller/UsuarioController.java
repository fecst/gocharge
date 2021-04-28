package br.com.gocharg.controller;

import br.com.gocharg.command.CommandContext;
import br.com.gocharg.domain.defaultResponses.FluentResponse;
import br.com.gocharg.dto.UsuarioDTO;
import br.com.gocharg.exceptions.BadRequestException;
import br.com.gocharg.mappers.UsuarioMapper;
import br.com.gocharg.mappers.VeiculoMapper;
import br.com.gocharg.processor.usuario.*;
import br.com.gocharg.processor.veiculo.BuscaVeiculoPorUsuarioProcessor;
import br.com.gocharg.validator.UsuarioValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class UsuarioController {

  @Autowired private BuscaUsuariosProcessor buscaUsuariosProcessor;
  @Autowired private BuscaUsuarioPorIdProcessor buscaUsuarioPorIdProcessor;
  @Autowired private ApagaUsuarioPorIdProcessor apagaUsuarioPorIdProcessor;
  @Autowired private CadastraUsuarioProcessor cadastraUsuarioProcessor;
  @Autowired private AlteraUsuarioProcessor alteraUsuarioProcessor;
  @Autowired private UsuarioValidator usuarioValidator;
  @Autowired private BuscaVeiculoPorUsuarioProcessor buscaVeiculoPorUsuarioProcessor;

  @GetMapping("/usuarios")
  public ResponseEntity<Object> getUsuarios() {
    return ResponseEntity.ok()
        .body(
            FluentResponse.success()
                .data(UsuarioMapper.INSTANCE.toDTO(buscaUsuariosProcessor.process(null))));
  }

  @GetMapping("/usuarios/{id_usuario}")
  public ResponseEntity<Object> getUsuarioPorId(@PathVariable("id_usuario") String idUsuario) {
    CommandContext context = new CommandContext();
    context.put("idUsuario", UUID.fromString(idUsuario));

    return ResponseEntity.ok()
            .body(
                    FluentResponse.success()
                            .data(UsuarioMapper.INSTANCE.toDTO(buscaUsuarioPorIdProcessor.process(context))));
  }

  @GetMapping("/usuarios/{id_usuario}/veiculos")
  public ResponseEntity<Object> getVeiculosPorUsuario(@PathVariable("id_usuario") String idUsuario) {
    CommandContext context = new CommandContext();
    context.put("idUsuario", UUID.fromString(idUsuario));

    return ResponseEntity.ok()
            .body(
                    FluentResponse.success()
                            .data(VeiculoMapper.INSTANCE.toDTO(buscaVeiculoPorUsuarioProcessor.process(context))));
  }

  @PostMapping("/usuarios")
  public ResponseEntity<Object> postUsuario(@RequestBody UsuarioDTO usuario) {
    usuarioValidator.validate(usuario).isInvalidThrow(BadRequestException.class);

    CommandContext context = new CommandContext();
    context.put("usuarioDTO", usuario);

    return ResponseEntity.status(HttpStatus.CREATED)
        .body(
            FluentResponse.success()
                .data(UsuarioMapper.INSTANCE.toDTO(cadastraUsuarioProcessor.process(context))));
  }

  @PutMapping("/usuarios/{id_usuario}")
  public ResponseEntity<Object> putUsuario(
      @PathVariable("id_usuario") String idUsuario, @RequestBody UsuarioDTO usuario) {
    usuarioValidator.validate(usuario).isInvalidThrow(BadRequestException.class);

    usuario.setId(idUsuario);

    CommandContext context = new CommandContext();
    context.put("usuarioDTO", usuario);

    return ResponseEntity.ok()
        .body(
            FluentResponse.success()
                .data(UsuarioMapper.INSTANCE.toDTO(alteraUsuarioProcessor.process(context))));
  }

  @DeleteMapping("/usuarios/{id_usuario}")
  public ResponseEntity<Object> deleteUsuario(@PathVariable("id_usuario") String idUsuario) {
    CommandContext context = new CommandContext();
    context.put("idUsuario", UUID.fromString(idUsuario));

    return ResponseEntity.accepted().body(apagaUsuarioPorIdProcessor.process(context));
  }
}
