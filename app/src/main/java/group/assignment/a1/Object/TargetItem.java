package group.assignment.a1.Object;

public class TargetItem {
    private String name;
    private int iconResource;
    private int day;
    private int id = 1;
    private long currentDate;
    private int status;

    public TargetItem(String name, int iconResource, int day) {
        this.name = name;
        this.iconResource = iconResource;
        this.day = day;
    }

    public TargetItem(String name, int iconResource, int day, int id, int status) {
        this.name = name;
        this.iconResource = iconResource;
        this.day = day;
        this.id = id;
        this.status = status;
    }

    public TargetItem(String name, int iconResource, int day, int id, long currentDate, int status) {
        this.name = name;
        this.iconResource = iconResource;
        this.day = day;
        this.id = id;
        this.currentDate = currentDate;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public int getIconResource() {
        return iconResource;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setTargetId(int id) {
        this.id = id;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getTargetId() {
        return this.id;
    }

}
