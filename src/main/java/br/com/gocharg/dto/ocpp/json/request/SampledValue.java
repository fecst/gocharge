package br.com.gocharg.dto.ocpp.json.request;

import br.com.gocharg.enums.ocpp.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class SampledValue {
  private String value;
  private OcppContextEnum context;
  private OcppFormatEnum format;
  private OcppMeasurandEnum measurand;
  private OcppPhaseEnum phase;
  private OcppLocationEnum location;
  private OcppUnitEnum unit;
}
