package br.com.corrida_kart.utils;

import java.time.LocalTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TimeUtils {

    public static String sumTimes(String timeString1, String timeString2) {
        LocalTime time1 = parseTime(timeString1);
        LocalTime time2 = parseTime(timeString2);

        if (time1 != null && time2 != null) {
            LocalTime sum = time1.plusHours(time2.getHour())
                    .plusMinutes(time2.getMinute())
                    .plusSeconds(time2.getSecond())
                    .plusNanos(time2.getNano());

            System.out.println("Input String 1: " + time1);
            System.out.println("Input String 2: " + time2);
            System.out.println("Sum of times: " + sum);
            return sum.toString();
        } else {
            System.out.println("Invalid input format.");
            return "";
        }
    }

    private static LocalTime parseTime(String input) {
        Pattern pattern = Pattern.compile("(\\d+):(\\d+):(\\d+),(\\d+)");
        Matcher matcher = pattern.matcher(input);

        if (matcher.matches()) {
            int hours = Integer.parseInt(matcher.group(1));
            int minutes = Integer.parseInt(matcher.group(2));
            int seconds = Integer.parseInt(matcher.group(3));
            int nanos = Integer.parseInt(matcher.group(4)) * 1_000_000;

            return LocalTime.of(hours, minutes, seconds).plusNanos(nanos);
        } else {
            pattern = Pattern.compile("(\\d+):(\\d+).(\\d+)");
            matcher = pattern.matcher(input);

            if (matcher.matches()) {
                int minutes = Integer.parseInt(matcher.group(1));
                int seconds = Integer.parseInt(matcher.group(2));
                int nanos = Integer.parseInt(matcher.group(3)) * 1_000_000;

                return LocalTime.of(0, minutes, seconds).plusNanos(nanos);
            } else {
                return null;
            }
        }
    }
}
