package group.assignment.a1.Object;

public class TargetDate {
    String targetDate_title = "Unknown";
    String targetDate_date = "NULL";
    int targetDate_day;
    int targetId;

    public TargetDate(String targetDate_title, String targetDate_date, int targetDate_day) {
        this.targetDate_title = targetDate_title;
        this.targetDate_date = targetDate_date;
        this.targetDate_day = targetDate_day;
    }

    public TargetDate(String targetDate_title, String targetDate_date, int targetDate_day, int targetId) {
        this.targetDate_title = targetDate_title;
        this.targetDate_date = targetDate_date;
        this.targetDate_day = targetDate_day;
        this.targetId = targetId;
    }

    public String getTargetDate_title() {
        return targetDate_title;
    }

    public int getTargetDate_day() {
        return targetDate_day;
    }

    @Override
    public String toString() {
        return targetDate_title;
    }

    public int getTargetId(){return targetId;}

}
