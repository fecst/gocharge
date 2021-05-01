package br.com.gocharg.ocpp.json.response;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.criteria.CriteriaBuilder;

@Getter
@Setter
public class ChargingSchedulePeriod {
    private Integer startPeriod;
    private Number limit;
    private Integer numberPhases;
}
