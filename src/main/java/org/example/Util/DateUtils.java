package org.example.Util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public static LocalDate toLocalDate(String data) {
        return LocalDate.parse(data, DATE_FORMATTER);
    }

    public static LocalDateTime toLocalDateTime(String dateTime) {
        return LocalDateTime.parse(dateTime, DATE_TIME_FORMATTER);
    }

    public static String format(LocalDate data) {
        return data.format(DATE_FORMATTER);
    }

    public static String format(LocalDateTime dataHora) {
        return dataHora.format(DATE_TIME_FORMATTER);
    }
}
