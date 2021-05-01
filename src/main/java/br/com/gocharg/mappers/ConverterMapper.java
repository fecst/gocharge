package br.com.gocharg.mappers;

import br.com.gocharg.exceptions.UnprocessableEntityException;
import org.apache.commons.lang.StringUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Mapper
public interface ConverterMapper {

    String STRING_TO_LOCAL_DATE_TIME = "stringToLocalDateTime";
    String STRING_TO_DATE = "stringToDate";
    String STRING_TO_UUID = "stringToUUID";
    String UUID_TO_STRING = "uuidToString";
    String LOCAL_DATE_TIME_TO_STRING = "localDateTimeToString";
    String DATE_TO_STRING = "dateToString";
    String STRING_TO_INTEGER = "stringToInt";
    String INTEGER_TO_STRING = "integerToString";
    String DOUBLE_TO_STRING = "doubleToString";
    String STRING_TO_DOUBLE = "stringToDouble";
    String BIG_DECIMAL_TO_STRING = "bigDecimalToString";
    String STRING_TO_BIG_DECIMAL = "stringToBigDecimal";
    String STRING_TO_BOOLEAN = "stringToBoolean";
    String BOOLEAN_TO_STRING = "booleanToString";

    @Named(STRING_TO_BOOLEAN)
    default Boolean stringToBoolean(String valor) {
        return StringUtils.isNotBlank(valor) ? Boolean.valueOf(valor) : null;
    }

    @Named(BOOLEAN_TO_STRING)
    default String booleanToString(Boolean valor) {
        return Objects.nonNull(valor) ? valor.toString() : null;
    }

    @Named(STRING_TO_BIG_DECIMAL)
    default BigDecimal stringToBigDecimal(String valor) {
        return StringUtils.isNotBlank(valor) ? new BigDecimal(valor) : null;
    }

    @Named(BIG_DECIMAL_TO_STRING)
    default String bigDecimalToString(BigDecimal valor) {
        return Objects.nonNull(valor) ? valor.toString() : null;
    }

    @Named(STRING_TO_DOUBLE)
    default Double stringToDouble(String valor) {
        return StringUtils.isNotBlank(valor) ? Double.valueOf(valor) : null;
    }

    @Named(DOUBLE_TO_STRING)
    default String doubleToString(Double valor) {
        return Objects.nonNull(valor) ? valor.toString() : null;
    }

    @Named(STRING_TO_LOCAL_DATE_TIME)
    default LocalDateTime stringToLocalDateTime(String datetime) {
        return StringUtils.isNotBlank(datetime) ? LocalDateTime.parse(datetime) : null;
    }

    @Named(STRING_TO_DATE)
    default Date stringToDate(String date) {
        try {
            return StringUtils.isNotBlank(date) ? new SimpleDateFormat("yyyy-MM-dd").parse(date) : null;
        } catch (Exception e) {
            throw new UnprocessableEntityException("Data com formato incorreto (yyyy-MM-dd)");
        }
    }

    @Named(STRING_TO_UUID)
    default UUID stringToUUID(String id) {
        return Objects.nonNull(id) ? UUID.fromString(id) : null;
    }

    @Named(UUID_TO_STRING)
    default String uuidToString(UUID id) {
        return Objects.nonNull(id) ? id.toString() : null;
    }

    @Named(LOCAL_DATE_TIME_TO_STRING)
    default String localDateTimeToString(LocalDateTime localDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return Objects.nonNull(localDateTime) ? localDateTime.format(formatter) : null;
    }

    @Named(DATE_TO_STRING)
    default String dateToString(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return Objects.nonNull(date) ? dateFormat.format(date) : null;
    }

    @Named(STRING_TO_INTEGER)
    default Integer stringToInteger(String id) {
        try {
            return Objects.nonNull(id) ? Integer.valueOf(id) : null;
        } catch (Exception e) {
            throw new UnprocessableEntityException("Valor informado não é um número inteiro válido");
        }
    }

    @Named(INTEGER_TO_STRING)
    default String integerToString(Integer id) {
        return Objects.nonNull(id) ? id.toString() : null;
    }
}
