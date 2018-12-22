package Model;

public class Alarm {
    public int status;
    public String timeSet;

    public Alarm(int status,String time) {
        this.status = status;
        this.timeSet = time;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTimeSet() {
        return timeSet;
    }

    public void setTimeSet(String timeSet) {
        this.timeSet = timeSet;
    }

    @Override
    public String toString() {
        return "Alarm{" +
                "status=" + status +
                ", timeSet='" + timeSet + '\'' +
                '}';
    }
}
