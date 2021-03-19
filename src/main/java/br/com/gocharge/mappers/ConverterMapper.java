package br.com.gocharge.mappers;

import org.apache.commons.lang.StringUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Mapper
public interface ConverterMapper {

    String STRING_TO_LOCAL_DATE_TIME = "StringToLocalDateTime";

  @Named(STRING_TO_LOCAL_DATE_TIME)
  default LocalDateTime stringToLocalDateTime(String datetime) {
    return StringUtils.isNotBlank(datetime)
        ? LocalDateTime.parse(datetime)
        : null;
  }
}
