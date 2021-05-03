package br.com.gocharg.dto.ocpp.json.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class MeterValue {
  private List<SampledValue> sampledValue;

  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
  private Date timestamp;
}
