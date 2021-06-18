package br.com.gocharg.controller;

import br.com.gocharg.command.CommandContext;
import br.com.gocharg.processor.ocpp.OcppProcessor;
import br.com.gocharg.processor.ocpp.down.request.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class OcppController {

  @Autowired private OcppProcessor ocppProcessor;
  @Autowired private RemoteStartTransactionRequestProcessor remoteStartTransactionRequestProcessor;
  @Autowired private RemoteStopTransactionRequestProcessor remoteStopTransactionRequestProcessor;
  @Autowired private ReserveNowRequestProcessor reserveNowRequestProcessor;
  @Autowired private CancelReservationRequestProcessor cancelReservationRequestProcessor;
  @Autowired private ChangeAvailabilityRequestProcessor changeAvailabilityRequestProcessor;
  @Autowired private ClearCacheRequestProcessor clearCacheRequestProcessor;
  @Autowired private ResetRequestProcessor resetRequestProcessor;
  @Autowired private UnlockConnectorRequestProcessor unlockConnectorRequestProcessor;

  @PostMapping("/ocpp")
  public ResponseEntity<Object> receive(@RequestBody Object[] ocppMessage) {
    CommandContext context = new CommandContext();
    context.put("ocppMessage", ocppMessage);

    return ResponseEntity.status(HttpStatus.OK).body(ocppProcessor.process(context));
  }

  @GetMapping("/charge_point/start/{apelido_totem}/{usuario}")
  public ResponseEntity<Object> start(
      @PathVariable("apelido_totem") String apelidoTotem, @PathVariable("usuario") String usuario) {
    CommandContext context = new CommandContext();

    context.put("apelidoTotem", apelidoTotem);
    context.put("usuario", usuario);

    return ResponseEntity.status(HttpStatus.OK)
        .body(remoteStartTransactionRequestProcessor.process(context));
  }

  @GetMapping("/charge_point/stop/{apelido_totem}")
  public ResponseEntity<Object> stop(@PathVariable("apelido_totem") String apelidoTotem) {
    CommandContext context = new CommandContext();
    context.put("apelidoTotem", apelidoTotem);

    return ResponseEntity.status(HttpStatus.OK)
        .body(remoteStopTransactionRequestProcessor.process(context));
  }

  @GetMapping("/charge_point/reserve_now/{apelido_totem}/{usuario}")
  public ResponseEntity<Object> reserveNow(
          @PathVariable("apelido_totem") String apelidoTotem, @PathVariable("usuario") String usuario) {
    CommandContext context = new CommandContext();

    context.put("apelidoTotem", apelidoTotem);
    context.put("usuario", usuario);

    return ResponseEntity.status(HttpStatus.OK)
            .body(reserveNowRequestProcessor.process(context));
  }

  @GetMapping("/charge_point/cancel_reservation/{apelido_totem}/{usuario}")
  public ResponseEntity<Object> cancelReservation(
          @PathVariable("apelido_totem") String apelidoTotem, @PathVariable("usuario") String usuario) {
    CommandContext context = new CommandContext();

    context.put("apelidoTotem", apelidoTotem);
    context.put("usuario", usuario);

    return ResponseEntity.status(HttpStatus.OK)
            .body(cancelReservationRequestProcessor.process(context));
  }

  @GetMapping("/charge_point/change_availability/{apelido_totem}/{availability}")
  public ResponseEntity<Object> changeAvailability(@PathVariable("apelido_totem") String apelidoTotem,
                                                   @PathVariable("availability") String disponibilidade) {
    CommandContext context = new CommandContext();
    context.put("apelidoTotem", apelidoTotem);
    context.put("disponibilidade", disponibilidade);

    return ResponseEntity.status(HttpStatus.OK)
            .body(changeAvailabilityRequestProcessor.process(context));
  }

  @GetMapping("/charge_point/clear_cache/{apelido_totem}")
  public ResponseEntity<Object> changeAvailability(@PathVariable("apelido_totem") String apelidoTotem) {
    CommandContext context = new CommandContext();
    context.put("apelidoTotem", apelidoTotem);

    return ResponseEntity.status(HttpStatus.OK)
            .body(clearCacheRequestProcessor.process(context));
  }

  @GetMapping("/charge_point/reset/{apelido_totem}/{tipo_reset}")
  public ResponseEntity<Object> reset(@PathVariable("apelido_totem") String apelidoTotem,
                                                   @PathVariable("tipo_reset") String tipoReset) {
    CommandContext context = new CommandContext();
    context.put("apelidoTotem", apelidoTotem);
    context.put("tipoReset", tipoReset);

    return ResponseEntity.status(HttpStatus.OK)
            .body(resetRequestProcessor.process(context));
  }

  @GetMapping("/charge_point/unlock_connector/{apelido_totem}")
  public ResponseEntity<Object> unlockConnector(@PathVariable("apelido_totem") String apelidoTotem) {
    CommandContext context = new CommandContext();
    context.put("apelidoTotem", apelidoTotem);

    return ResponseEntity.status(HttpStatus.OK)
            .body(unlockConnectorRequestProcessor.process(context));
  }
}
