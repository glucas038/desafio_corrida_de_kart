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

            return sum.toString();

        } else {
            System.out.println("Invalid input format.");
            return "";
        }
    }

    public static String subtractTimes(String timeString1, String timeString2) {
        LocalTime time1 = parseTime(timeString1);
        LocalTime time2 = parseTime(timeString2);

        if (time1 != null && time2 != null) {
            LocalTime difference = time1.minusHours(time2.getHour())
                    .minusMinutes(time2.getMinute())
                    .minusSeconds(time2.getSecond())
                    .minusNanos(time2.getNano());

            return difference.toString();

        } else {
            System.out.println("Invalid input format.");
            return "";
        }
    }

    public static LocalTime parseTime(String input) {
        input = input.replace(",",".");
        Pattern pattern = Pattern.compile("(?:(\\d+):)?(\\d+)(?::(\\d+))?(?:\\.(\\d+))?");
        Matcher matcher = pattern.matcher(input);

        if (matcher.matches()) {
            int hours = matcher.group(1) != null ? Integer.parseInt(matcher.group(1)) : 0;
            int minutes = Integer.parseInt(matcher.group(2));
            int seconds = matcher.group(3) != null ? Integer.parseInt(matcher.group(3)) : 0;
            int nanos = matcher.group(4) != null ? Integer.parseInt(matcher.group(4)) * 1_000_000 : 0;

            return LocalTime.of(hours, minutes, seconds).plusNanos(nanos);
        } else {
            return null;
        }
    }
}
