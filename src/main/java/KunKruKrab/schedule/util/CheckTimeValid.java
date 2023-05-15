package KunKruKrab.schedule.util;

public class CheckTimeValid {
    public static boolean verify(String startTime, String endTime) {
        String[] startParts = startTime.split(":");
        int startHours = Integer.parseInt(startParts[0]);
        int startMinutes = Integer.parseInt(startParts[1]);

        String[] endParts = endTime.split(":");
        int endHours = Integer.parseInt(endParts[0]);
        int endMinutes = Integer.parseInt(endParts[1]);

        int startSeconds = startHours * 3600 + startMinutes * 60;
        int endSeconds = endHours * 3600 + endMinutes * 60;

        return startSeconds <= endSeconds;
    }
}
