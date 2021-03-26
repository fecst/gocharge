package br.com.gocharge.mappers;

import org.apache.commons.lang.StringUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.sql.Date;
import java.time.LocalDateTime;

@Mapper
public interface ConverterMapper {

    String STRING_TO_LOCAL_DATE_TIME = "StringToLocalDateTime";
    String STRING_TO_DATE = "StringToDate";

    @Named(STRING_TO_LOCAL_DATE_TIME)
    default LocalDateTime stringToLocalDateTime(String datetime) {
        return StringUtils.isNotBlank(datetime) ? LocalDateTime.parse(datetime) : null;
    }

    @Named(STRING_TO_DATE)
    default Date StringToDate(String date) {
        return StringUtils.isNotBlank(date) ? Date.valueOf(date) : null;
    }
}
