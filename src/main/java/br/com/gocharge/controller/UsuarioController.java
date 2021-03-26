package br.com.gocharge.controller;

import br.com.gocharge.command.CommandContext;
import br.com.gocharge.domain.Usuario;
import br.com.gocharge.domain.defaultResponses.FluentResponse;
import br.com.gocharge.exceptions.BadRequestException;
import br.com.gocharge.processor.usuario.*;
import br.com.gocharge.validator.UsuarioValidator;
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

  @GetMapping("/usuarios")
  public ResponseEntity<Object> getUsuarios() {
    return ResponseEntity.ok()
        .body(FluentResponse.success().data(buscaUsuariosProcessor.process(null)));
  }

  @GetMapping("/usuarios/{id_usuario}")
  public ResponseEntity<Object> getUsuarioPorId(@PathVariable("id_usuario") String idUsuario) {
    CommandContext context = new CommandContext();
    context.put("idUsuario", UUID.fromString(idUsuario));

    return ResponseEntity.ok()
        .body(FluentResponse.success().data(buscaUsuarioPorIdProcessor.process(context)));
  }

  @PostMapping("/usuarios")
  public ResponseEntity<Object> postUsuario(@RequestBody Usuario usuario) {
    usuario.setCreate(true);

    usuarioValidator.validate(usuario).isInvalidThrow(BadRequestException.class);

    CommandContext context = new CommandContext();
    context.put("usuario", usuario);

    return ResponseEntity.status(HttpStatus.CREATED)
        .body(FluentResponse.success().data(cadastraUsuarioProcessor.process(context)));
  }

  @PutMapping("/usuarios/{id_usuario}")
  public ResponseEntity<Object> putUsuario(
      @PathVariable("id_usuario") String idUsuario, @RequestBody Usuario usuario) {
    usuarioValidator.validate(usuario).isInvalidThrow(BadRequestException.class);

    usuario.setId(UUID.fromString(idUsuario));

    CommandContext context = new CommandContext();
    context.put("usuario", usuario);

    return ResponseEntity.ok()
        .body(FluentResponse.success().data(alteraUsuarioProcessor.process(context)));
  }

  @DeleteMapping("/usuarios/{id_usuario}")
  public ResponseEntity<Object> deleteUsuario(@PathVariable("id_usuario") String idUsuario) {
    CommandContext context = new CommandContext();
    context.put("idUsuario", UUID.fromString(idUsuario));

    return ResponseEntity.accepted().body(apagaUsuarioPorIdProcessor.process(context));
  }
}
