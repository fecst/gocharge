package br.com.gocharg.domain;

import br.com.gocharg.model.TotemModel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class Transacao {
  private UUID id;
  private LocalDateTime dataHoraCadastro;
  private Integer operation;
  private Integer uniqueID;
  private String action;
  private String payload;
  private Totem totem;
}
