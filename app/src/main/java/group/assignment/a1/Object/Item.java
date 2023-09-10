package group.assignment.a1.Object;

public class Item {
    String name;
    int path;
    int day;

    public Item(String name, int path, int day) {
        this.name = name;
        this.path = path;
        this.day = day;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPath() {
        return path;
    }

    public void setPath(int path) {
        this.path = path;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }
}
