package group.assignment.a1;

public class CountDownItem {
        String title;
        String description;
        String date;
        int notification;

        public CountDownItem(String title, String description, String date, int notification) {
            this.title = title;
            this.description = description;
            this.date = date;
            this.notification = notification;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public int getNotification() {
            return notification;
        }

        public void setNotification(int notification) {
            this.notification = notification;
        }
}
