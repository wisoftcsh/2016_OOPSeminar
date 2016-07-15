package quiz.australianvotinghash;

public class Australian_votingEx {
    public static void main(String[] args) {
        TextInOut tio = new TextInOut();
        tio.Input();
        tio.print();
        Voting v = new Voting();
        v.read();
    }
}
