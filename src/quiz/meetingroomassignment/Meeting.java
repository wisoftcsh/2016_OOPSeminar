package quiz.meetingroomassignment;

public class Meeting {
    private int number = 0;
    private int startTime = 0;
    private int endTime = 0;
    private int difference = 0;

    Meeting(int number, int startTime, int endTime) {
        setNumber(number);
        setStartTime(startTime);
        setEndTime(endTime);
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    public void setDifference() {
        this.difference = endTime - startTime;
    }

    public int getDifference() {
        return this.difference;
    }

    public int getNumber() {
        return this.number;
    }

    public int getStartTime() {
        return this.startTime;
    }

    public int getEndTime() {
        return this.endTime;
    }
}
