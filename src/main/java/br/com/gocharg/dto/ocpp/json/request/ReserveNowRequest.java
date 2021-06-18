package br.com.gocharg.dto.ocpp.json.request;

import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Getter
@Setter
public class ReserveNowRequest {
  private Integer connectorId;
  private String expiryDate;
  private String idTag;
  private String parentIdTag;
  private BigInteger reservationId;
}
