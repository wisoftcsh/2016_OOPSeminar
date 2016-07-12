package chap01.australianvotinghash;

public class Candidate {
    String name;
    int NumberCounter = 0;

    Candidate(String name) {
        this.name = name;
    }

    public void setNumberCounter() {
        NumberCounter++;
    }

    public int getNumberCounter() {
        return NumberCounter;
    }

    public String getName() {
        return name;
    }
}
