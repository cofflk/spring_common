package com.haeahn.common.global.utils.common;


import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class DateUtils {


    private DateUtils() {
        throw new UnsupportedOperationException("Utility class");
    }

    // 현재 날짜/시간 반환
    public static LocalDate nowDate() {
        return LocalDate.now();
    }

    public static LocalDateTime nowDateTime() {
        return LocalDateTime.now();
    }

    // 문자열 → LocalDate
    public static LocalDate parseToDate(String dateStr, String pattern) {
        return LocalDate.parse(dateStr, DateTimeFormatter.ofPattern(pattern));
    }

    // LocalDate → 문자열
    public static String format(LocalDate date, String pattern) {
        return date.format(DateTimeFormatter.ofPattern(pattern));
    }


    // 하루의 시작 시간
    public static LocalDateTime startOfDay(LocalDate date) {
        return date.atStartOfDay();
    }

    // 하루의 종료 시간
    public static LocalDateTime endOfDay(LocalDate date) {
        return date.atTime(LocalTime.MAX);
    }
//    ====================
    private static final ZoneId KST = ZoneId.of("Asia/Seoul");


//    // Date → LocalDateTime
//    public static LocalDateTime toLocalDateTime(Date date) {
//        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
//    }
//
//    // LocalDateTime → Date
//    public static Date toDate(LocalDateTime dateTime) {
//        return Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
//    }

    // LocalDateTime → java.util.Date (KST 기준)
    public static Date toDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(KST).toInstant());
    }

    // java.util.Date → LocalDateTime (KST 기준)
    public static LocalDateTime toLocalDateTime(Date date) {
        return date.toInstant().atZone(KST).toLocalDateTime();
    }

    // 현재 한국 시간 (LocalDate, LocalDateTime)
    public static LocalDate nowKSTDate() {
        return LocalDate.now(KST);
    }

    public static LocalDateTime nowKSTDateTime() {
        return LocalDateTime.now(KST);
    }

    // LocalDateTime -> String
    public static String format(LocalDateTime dateTime, String pattern) {
        return dateTime.format(DateTimeFormatter.ofPattern(pattern));
    }

    // 문자열 -> LocalDateTime
    public static LocalDateTime parseToDateTime(String datetimeStr, String pattern) {
        return LocalDateTime.parse(datetimeStr, DateTimeFormatter.ofPattern(pattern));
    }

    // UTC → KST 변환
    public static LocalDateTime convertUtcToKst(LocalDateTime utcDateTime) {
        ZonedDateTime utcZoned = utcDateTime.atZone(ZoneId.of("UTC"));
        return utcZoned.withZoneSameInstant(KST).toLocalDateTime();
    }

    // KST → UTC 변환
    public static LocalDateTime convertKstToUtc(LocalDateTime kstDateTime) {
        ZonedDateTime kstZoned = kstDateTime.atZone(KST);
        return kstZoned.withZoneSameInstant(ZoneId.of("UTC")).toLocalDateTime();
    }


    // 날짜 차이 (일 단위)
    public static long daysBetween(LocalDate start, LocalDate end) {
        return ChronoUnit.DAYS.between(start, end);
    }

    // 시간 차이 (분 단위)
    public static long minutesBetween(LocalDateTime start, LocalDateTime end) {
        return ChronoUnit.MINUTES.between(start, end);
    }

}
